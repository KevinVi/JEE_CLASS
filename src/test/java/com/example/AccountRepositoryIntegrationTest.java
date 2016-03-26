package com.example;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

/**
 * Created by kevin on 11/03/2016.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@Sql(
        statements = {
                "insert into Account (uuid, balance) values ('abc-123',30)",
                "insert into Account (uuid, balance) values ('abc-523',50)",
                "insert into Account (uuid, balance) values ('abc-643',60)"
        },
        executionPhase = BEFORE_TEST_METHOD
)
@Sql(
        statements = {
                "delete from Account"
        },
        executionPhase = AFTER_TEST_METHOD
)
public class AccountRepositoryIntegrationTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void should_find_all() {
        assertThat(accountRepository.findAll(), hasSize(3));
    }

    @Test
    public void should_find_by_balance() {
        assertThat(accountRepository.findByBalance(50), hasSize(1));
    }

    @Test
    public void should_get_by_balance() {
        assertThat(accountRepository.getByBalance(50), hasSize(1));
    }

    @Test
    public void should_get_paginated_accounts(){
        int page = 0;
        int size = 2;
        PageRequest pageRequest = new PageRequest(page,size);

        Page<Account> firstPage = accountRepository.findAllBy(pageRequest);
        Page<Account> secondPage = accountRepository.findAllBy(firstPage.nextPageable());
        assertThat(firstPage.getTotalElements(), is(3L));

        assertThat(firstPage.getContent(),hasSize(2));
        assertThat(secondPage.getContent(),hasSize(1));
    }

    @Test
    public void should_get_stream(){
        try(Stream<Account> stream = accountRepository.findAllBy()){

            assertThat(stream.peek(System.out::println).collect(toList()),hasSize(3));
        }
    }
    @Test
    public void should_find_all_nio() throws ExecutionException,InterruptedException{
        accountRepository.findByUuid("abc-523");


    }

}
