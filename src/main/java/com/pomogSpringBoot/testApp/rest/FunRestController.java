package com.pomogSpringBoot.testApp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    
    public FunRestController() {
        System.out.printf("inside constructor: %s\n",  getClass().getSimpleName());
    }
    
    @Value("${testMessage}")
    private String testMessage;
    
    @GetMapping("/")
    public String sayHello(){
        return "Hello! spring-boot-devtools";
    }
    
    @GetMapping("/devtools")
    public String devtools(){
        return "this.testMessage";
    }
    
}
