package com.pomogSpringBoot.testApp.service;

import com.pomogSpringBoot.testApp.entity.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByUserName(String userName);
}