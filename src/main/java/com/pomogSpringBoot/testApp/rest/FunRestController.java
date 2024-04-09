package com.pomogSpringBoot.testApp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    
    @GetMapping("/")
    public String sayHello(){
        return "Hello! spring-boot-devtools";
    }
    
    @GetMapping("/devtools")
    public String devtools(){
        return "spring-boot-devtools is OK!";
    }
    
}
