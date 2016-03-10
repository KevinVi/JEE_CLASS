package com.example;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.mapper.ObjectMapper;
import groovy.json.JsonException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
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
    public void add_account(){

        final int balance = 60;
        Account accountToCreate = Account.builder().balance(balance).build();
        given()
                .contentType(JSON)
                .body(accountToCreate)
            .when()
                .post("/accounts")
                .then()
                .statusCode(201)
                .body("balance",is(balance));


    }
}
