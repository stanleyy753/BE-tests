package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.dto.post.UserCreationResponse;
import org.example.dto.post.UserRequest;
import org.example.util.TestDataUtils;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.lessThan;


public class PostUsersTest extends BaseTest {

    @Test
    public void testPostUser() {

        List<UserRequest> users = TestDataUtils.loadUsersFromJson("/data/users.json");

        // post all users from json file
        for (UserRequest request : users) {
            UserCreationResponse userResponse = RestAssured
                    .given()
                    .relaxedHTTPSValidation()
                    .contentType(ContentType.JSON)
                    .body(request)
                    .when()
                    .post("/users")
                    .then()
                    .statusCode(201)
                    .time(lessThan((long) 1000))
                    .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user-response-schema.json"))
                    .extract()
                    .as(UserCreationResponse.class);


            // check user data
            assertThat(userResponse.getId()).isNotNull();
            assertThat(userResponse.getName()).isEqualTo(request.getName());
            assertThat(userResponse.getJob()).isEqualTo(request.getJob());

            //check timestamp
            OffsetDateTime createdAt = OffsetDateTime.parse(userResponse.getCreatedAt());
            OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

            assertThat(Math.abs(Duration.between(createdAt, now).toMillis()))
                    .isLessThan(5000); // 5 seconds

            System.out.println("Created user ID: " + userResponse.getId() +
                    ", Name: " + userResponse.getName());
        }
    }
}
