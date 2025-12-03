package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";

        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.protocol.handle-redirects", true)
                        .setParam("http.connection.timeout", 5000)
                        .setParam("http.socket.timeout", 5000)
                );

        RequestSpecification spec = new RequestSpecBuilder()
                .addHeader("x-api-key", "reqres_9706ad65db6c469bb35b897041be78a2")
                .build();

        RestAssured.requestSpecification = spec;
    }
}
