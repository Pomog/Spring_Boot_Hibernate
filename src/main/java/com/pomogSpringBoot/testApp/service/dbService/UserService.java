package com.pomogSpringBoot.testApp.service.dbService;

import com.pomogSpringBoot.testApp.dto.UserDTO;
import com.pomogSpringBoot.testApp.entity.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
    List<User> findAllUsers();
    void saveUser(User user);
}