package com.pomogSpringBoot.testApp.rest;

import com.pomogSpringBoot.testApp.common.inrerface.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final Coach myCoach;
    
    @Qualifier("secondCoach")
    @Autowired
    private Coach myNextCoach;
    
    @Qualifier("thirdCoach")
    @Autowired
    private Coach myCoachFieldInj;
    
    @Autowired
    public DemoController (Coach theCoach){
        System.out.printf("inside constructor: %s\n",  getClass().getSimpleName());
        myCoach = theCoach;
    }
    
    @Autowired
    public void setMyNextCoach(@Qualifier("secondCoach") Coach theCoachSetterAutowired){
        myNextCoach = theCoachSetterAutowired;
    }
    
    @GetMapping("/workout")
    public String getWorkout(){
        return myCoach.workout();
    }
    
    @GetMapping("/workout2")
    public String getWorkoutSetterAutowired(){
        return myNextCoach.workout();
    }
    
    @GetMapping("/workout3")
    public String getWorkoutFieldAutowired(){
        return myCoachFieldInj.workout();
    }
}
