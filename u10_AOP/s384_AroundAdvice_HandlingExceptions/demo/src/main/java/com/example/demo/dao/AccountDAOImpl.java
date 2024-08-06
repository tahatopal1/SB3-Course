package com.example.demo.dao;

import com.example.demo.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class AccountDAOImpl implements AccountDAO {

    private String name;
    private String serviceCode;

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public void addAccount(Account account, boolean vipFlag) {
        log.info("{}: DOING MY DB WORK: ADDING AN ACCOUNT", getClass());
    }

    @Override
    public boolean doWork() {
        log.info("{}: doWork()", getClass());
        return false;
    }

    public String getName() {
        log.info("{}: in getName()", getClass());
        return name;
    }

    public void setName(String name) {
        log.info("{}: in setName()", getClass());
        this.name = name;
    }

    public String getServiceCode() {
        log.info("{}: in getServiceCode()", getClass());
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        log.info("{}: in setServiceCode()", getClass());
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        // For academic purposes ... simulate an exception
        if (tripWire) {
            throw new RuntimeException("No soup for you!!!");
        }

        List<Account> accounts = new ArrayList<>();

        accounts.add(new Account("John", "Silver"));
        accounts.add(new Account("Jack", "Platinum"));
        accounts.add(new Account("Jane", "Gold"));

        return accounts;
    }
}
