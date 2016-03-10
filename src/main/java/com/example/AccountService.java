package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by kevin on 08/03/2016.
 */
@Service
public class AccountService {

    private List<Account> accounts = new ArrayList<>();
    private final AuthorizationService authorizationService;

    @Autowired
    public AccountService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
        asList(30, 40, 50).stream().map(Account::new).forEach(accounts::add);
    }


    public void updateMoney(Account account, int i) {
        Assert.notNull(account);
        if (account.getBalance() + i < 0)
            throw new CreditNotAuthorizedException();

        if (!authorizationService.isAllowed(account))
            throw new BlockedAccountException();
        account.setBalance(account.getBalance() + i);
    }

    /*
        class BlockeAuthorizationService implements AuthorizationService {
            @Override
            public boolean isAllowed(Account accountService) {
                return false;
            }
        }


        class AllwoedAuthorizationService implements AuthorizationService {
            @Override
            public boolean isAllowed(Account accountService) {
                return true;
            }
        }*/
    @PostConstruct
    public List<Account> findAccounts() {

        return accounts;
    }

    public Account insertAccount(Account account){
        accounts.add(account);
        return account;
    }
}
