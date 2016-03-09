package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by kevin on 09/03/2016.
 */
@RestController
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;

    }

    @RequestMapping(method = GET, value = "/accounts")
    public List<Account> getAccounts(){
        // return asList(30,20,50).stream().map(Account::new).collect(toList());
        /*return asList(
                Account.builder().balance(10).build(),
                Account.builder().balance(20).build(),
                Account.builder().balance(50).build()
        );*/
        return accountService.findAccounts();
    }
}
