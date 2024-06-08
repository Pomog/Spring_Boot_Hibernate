package com.pomogSpringBoot.testApp.controller;

import com.pomogSpringBoot.testApp.service.dbService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserService userServiceService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    public UserController(UserService userServiceService) {
        this.userServiceService = userServiceService;
    }
    
    @GetMapping("/userinfo")
    public String showAllUsers(Model theModel){
        theModel.addAttribute("users", userServiceService.findAllUsers());
        logger.info("All Users Info asked from the DB");
        
        return "userinfo-page";
    }
}
