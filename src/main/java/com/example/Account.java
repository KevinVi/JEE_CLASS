package com.example;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by kevin on 08/03/2016.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Account {
    private Integer balance;

    public Account(int b) {
        this.balance = b;
    }
}
