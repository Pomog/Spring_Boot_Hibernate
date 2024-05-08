package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.user.User;

public interface UserDao {
    User findByUserName(String userName);
}
