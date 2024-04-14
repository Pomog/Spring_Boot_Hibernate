package com.pomogSpringBoot.testApp.common.coach;

import com.pomogSpringBoot.testApp.common.inrerface.Coach;
import org.springframework.stereotype.Component;
/*
    for field injection
 */

@Component

public class ThirdCoach implements Coach {
    public ThirdCoach() {
        System.out.printf("inside constructor: %s\n",  getClass().getSimpleName());
    }
    
    @Override
    public String workout() {
        return "Practice for 35 min. ThirdCoach";
    }
}

