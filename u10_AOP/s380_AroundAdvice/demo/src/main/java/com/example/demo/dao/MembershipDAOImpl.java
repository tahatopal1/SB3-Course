package com.example.demo.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class MembershipDAOImpl implements MembershipDAO {

    @Override
    public boolean addMember() {
        log.info("{}: DOING MY DB WORK: ADDING AN MEMBERSHIP", getClass());
        return true;
    }

    @Override
    public void goToSleep() {
        log.info("{}: I'm going to sleep now...", getClass());
    }

}
