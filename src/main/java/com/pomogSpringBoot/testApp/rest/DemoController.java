package com.pomogSpringBoot.testApp.rest;

import com.pomogSpringBoot.testApp.common.inrerface.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final Coach myCoach;
    private final Coach myCoach2;
    
    @Qualifier("secondCoach")
    @Autowired
    private Coach myNextCoach;
    
    @Qualifier("thirdCoach")
    @Autowired
    private Coach myCoachFieldInj;
    
    @Autowired
    public DemoController (Coach theCoach, @Qualifier("aqua") Coach myCoach2){
        this.myCoach = theCoach;
        this.myCoach2 = myCoach2;
        System.out.printf("inside constructor: %s\n",  getClass().getSimpleName());
    }
    
    @Autowired
    public void setMyNextCoach(@Qualifier("secondCoach") Coach theCoachSetterAutowired){
        myNextCoach = theCoachSetterAutowired;
    }
    
    @GetMapping("/workout")
    public String getWorkout(){
        return myCoach.workout() + " " + myCoach.getCoachName();
    }
    
    @GetMapping("/workout2")
    public String getWorkoutSetterAutowired(){
        return myNextCoach.workout() + "  " + myNextCoach.getCoachName();
    }
    
    @GetMapping("/workout3")
    public String getWorkoutFieldAutowired(){
        return myCoachFieldInj.workout();
    }
    @GetMapping("/workout4")
    public String getWorkoutBeanAnnotation(){
        return myCoach2.workout() + " " + myCoach2.getCoachName();
    }
}
