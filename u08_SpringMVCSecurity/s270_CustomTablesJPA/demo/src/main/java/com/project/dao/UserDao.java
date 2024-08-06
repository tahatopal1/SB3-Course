package com.project.dao;

import com.project.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
}
