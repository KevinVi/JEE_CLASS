package com.example;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kevin on 08/03/2016.
 */
@Service
public class AuthorizationServiceImp implements AuthorizationService {
    @Override
    public boolean isAllowed(Account accountService) {
        return false;
    }

    @Override
    public List<Account> findAcconts() {

        return null;
    }
}
