package com.pomogSpringBoot.testApp.rest;

import com.pomogSpringBoot.testApp.inrerface.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;
    
    @Autowired
    public DemoController (Coach theCoach){
        myCoach = theCoach;
    }
    
    @GetMapping("/workout")
    public String getWorkout(){
        return myCoach.workout();
    }
}
