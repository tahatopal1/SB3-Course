package com.example.demo.dao;

import com.example.demo.model.Account;

public interface AccountDAO {

    void addAccount(Account account, boolean vipFlag);

    boolean doWork();

}
