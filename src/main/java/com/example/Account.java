package com.example;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by kevin on 08/03/2016.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Account {
    @Column(name="balance")
    private Integer balance;

    @Id
    private String uuid;

    public Account(int b) {
        this.balance = b;
    }
}
