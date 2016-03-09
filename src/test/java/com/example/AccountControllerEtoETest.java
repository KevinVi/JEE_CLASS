package com.example;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;

/**
 * Created by kevin on 09/03/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebIntegrationTest("server.port=9898")
public class AccountControllerEtoETest {
    @Before
    public void setup() {
        RestAssured.port = 9898;

    }

    @Test
    public void should_get_list_of_3_accounts() {
        given().log().all().when().get("/accounts").then().log().all().statusCode(200);
    }
}
