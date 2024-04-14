package com.pomogSpringBoot.testApp.common.coach;

import com.pomogSpringBoot.testApp.common.inrerface.Coach;
import org.springframework.stereotype.Component;

@Component
public class SecondCoach implements Coach {
    public SecondCoach() {
        System.out.printf("inside constructor: %s\n",  getClass().getSimpleName());
    }
    
    @Override
    public String workout() {
        return "Practice for 25 min. SecondCoach";
    }
    
    @Override
    public String getCoachName() {
        return null;
    }
}
