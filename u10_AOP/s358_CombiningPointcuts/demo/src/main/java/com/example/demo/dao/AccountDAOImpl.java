package com.example.demo.dao;

import com.example.demo.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class AccountDAOImpl implements AccountDAO {

    private String name;
    private String serviceCode;

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
}
