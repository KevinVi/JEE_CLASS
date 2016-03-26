package com.example;

import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

/**
 * Created by kevin on 09/03/2016.
 */

public class AccountControllerEtoETest extends AbstractBigTest {

    @Test
    public void should_get_list_of_3_accounts() {
        given().log().all().when().get("/accounts").then()
                .body("[0].balance", is(30))
                .body("$", hasSize(3))
                .log().all().statusCode(200);
    }

    @Test
    @DirtiesContext
    public void add_account() {

        final int balance = 60;
        Account accountToCreate = Account.builder().balance(balance).build();
        given()
                .contentType(JSON)
                //.body(accountToCreate)
                .body(toJson(accountToCreate))
            .when()
                .post("/accounts")
                .then()
                .statusCode(201)
                .body("balance", is(balance));

    }

    @Test
    @DirtiesContext
    public void validation_balance() {
        final int balance = 0;
        Account accountToCreate = Account.builder().balance(balance).build();
        given()
                .contentType(JSON)
                //.body(accountToCreate)
                .body(toJson(accountToCreate))
            .when()
                .post("/accounts")
                .then()
                .body("balance", is(balance));

    }
}
