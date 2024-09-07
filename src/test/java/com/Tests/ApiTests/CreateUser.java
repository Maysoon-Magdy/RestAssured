package com.Tests.ApiTests;
import org.testng.annotations.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import io.restassured.RestAssured ;

public class CreateUser {
    @BeforeClass
    public void setUp() {
        // Set the base URI for RestAssured
        RestAssured.baseURI = "https://reqres.in";
    }
    @Test
    public void createUser() {
        // Define the payload
        String payload = "{ \"name\": \"John Doe\", \"job\": \"Software Engineer\" }";

        // Send POST request to /api/users and validate the response
        given().
                header("Content-Type", "application/json").
                body(payload).
                when().
                post("/api/users").
                then().
                statusCode(201). // Assert that the status code is 201 Created
                body("name", equalTo("John Doe")). // Assert that the name in the response is correct
                body("job", equalTo("Software Engineer")); // Assert that the job in the response is correct
    }
}
