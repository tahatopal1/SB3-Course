package com.example.demo.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class MembershipDAOImpl implements MembershipDAO {

    @Override
    public void addAccount() {
        log.info("{}: DOING MY DB WORK: ADDING AN MEMBERSHIP", getClass());
    }

}
