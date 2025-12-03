package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    void setup() {
        RestAssured.reset();

        RestAssured.baseURI = "https://reqres.in/api";

        RequestSpecification spec = new RequestSpecBuilder()
                .addHeader("x-api-key", "reqres_9706ad65db6c469bb35b897041be78a2")
                .build();

        RestAssured.requestSpecification = spec;
    }
}
