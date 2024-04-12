package com.pomogSpringBoot.testApp.rest;

import com.pomogSpringBoot.testApp.common.inrerface.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;
    private Coach myNextCoach;
    
    @Autowired
    public DemoController (@Qualifier("cricketCoach") Coach theCoach){
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
}
