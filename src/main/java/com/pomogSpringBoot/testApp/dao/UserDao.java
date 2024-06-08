package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.user.User;

import java.util.List;

public interface UserDao {
    User findByUserName(String userName);
    
    List<User> findAllUsers();
}
