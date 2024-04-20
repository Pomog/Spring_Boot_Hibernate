package com.pomogSpringBoot.testApp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoRESTController {
    
    @GetMapping("hello")
    public String sayHello(){
        return "Hello World!";
    }
}
