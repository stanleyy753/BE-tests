package org.example.tests;

import io.restassured.RestAssured;
import org.example.dto.get.UserData;
import org.example.dto.get.UsersListResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetUsersTest extends BaseTest {

    @Test
    void testListGetUsers() {
        // collect users from all pages
        List<UserData> allUsers = new ArrayList<>();

        // fetch first page of users
        UsersListResponse firstPage = getUsersByPage(1);

        int totalPages = firstPage.getTotal_pages();
        int totalExpected = firstPage.getTotal();

        // add users from first page
        allUsers.addAll(firstPage.getData());

        // fetch and add remaining pages of users
        for (int page = 2; page <= totalPages; page++) {
            UsersListResponse nextPage = getUsersByPage(page);
            allUsers.addAll(nextPage.getData());
        }

        // check total users count
        assertThat(totalExpected).isEqualTo(12);

        // check last names of first 2 users in list
        assertThat(allUsers.get(0).getLast_name()).isEqualTo("Bluth");
        assertThat(allUsers.get(1).getLast_name()).isEqualTo("Weaver");

        // compare number of received users in “data” with received value “total”
        assertThat(allUsers.size()).isEqualTo(totalExpected);

        for (UserData user : allUsers) {
            // id should be integer
            assertThat(user.getId()).isInstanceOf(Integer.class);

            // email should be non-null string and match email pattern
            assertThat(user.getEmail()).isNotNull();
            assertThat(user.getEmail()).contains("@");

            // first_name and last_name should be non-null strings
            assertThat(user.getFirst_name()).isNotNull();
            assertThat(user.getLast_name()).isNotNull();

            // avatar should be non-null string containing URL
            assertThat(user.getAvatar()).isNotNull();
            assertThat(user.getAvatar()).startsWith("https://");
        }
    }

    public UsersListResponse getUsersByPage(int page) {
        return RestAssured
                .given()
                .queryParam("page", page)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .as(UsersListResponse.class);
    }
}