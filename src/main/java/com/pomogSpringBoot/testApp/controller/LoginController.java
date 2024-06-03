package com.pomogSpringBoot.testApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login-page")
    public String loginPage(){
        
        return "simple-login-page";
    }
}
