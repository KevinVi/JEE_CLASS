package com.example;

import groovy.transform.ASTTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Created by kevin on 11/03/2016.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    List<Account> findByBalance(Integer balance);

    @Query("select a from Account a where balance = :balance_param")
    List<Account> getByBalance(@Param("balance_param") Integer balance);

    Page<Account> findAllBy(Pageable pageRequest);

    Stream<Account> findAllBy();

    @Async
    CompletableFuture<List<Account>> findByUuid(String uuid);
}
