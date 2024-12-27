package com.hfenelsoftllc;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

@QuarkusTest
class AccountResourceTest {
    @Test
    void testWelcomeEndpoint() {
        given()
          .when().get("/accounts")
          .then()
             .statusCode(200)
             .body(is("Lakay Banking Services API"));
    }

}