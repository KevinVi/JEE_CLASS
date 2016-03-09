package com.example;

import java.util.List;

/**
 * Created by kevin on 08/03/2016.
 */
public interface AuthorizationService {
    boolean isAllowed(Account accountService);
    List<Account> findAcconts ();
}
