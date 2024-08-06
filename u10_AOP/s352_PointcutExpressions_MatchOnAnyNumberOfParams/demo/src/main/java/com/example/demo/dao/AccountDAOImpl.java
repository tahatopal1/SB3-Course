package com.example.demo.dao;

import com.example.demo.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class AccountDAOImpl implements AccountDAO {

    @Override
    public void addAccount(Account account, boolean vipFlag) {
        log.info("{}: DOING MY DB WORK: ADDING AN ACCOUNT", getClass());
    }

}
