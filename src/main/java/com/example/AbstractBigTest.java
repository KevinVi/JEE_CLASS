package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import groovy.json.JsonException;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

/**
 * Created by kevin on 09/03/2016.
 */
@ActiveProfiles("integration")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebIntegrationTest("server.port=8989")
public abstract class AbstractBigTest {


    @Configuration
    static class DatasourceConfiguration{
        /*@Bean
        @Profile("dev")
        public DataSource devDatasource(){
            return //H2
        }
        @Bean
        @Profile("integration")
        public DataSource devDatasource(){
            return //Mysql
        }*/

    }
    private static final Logger LOGGER = Logger.getLogger(AbstractBigTest.class);

    @Autowired
    ObjectMapper objectMapper;

    public <T> String toJson(T entity){
        try{
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            LOGGER.error("",e);
            return null;
        }
    }


    @Value("${server.port:9393}")
    private Integer port;
    @Before
    public void setupRestassured() {
        RestAssured.port = port;

    }

}
