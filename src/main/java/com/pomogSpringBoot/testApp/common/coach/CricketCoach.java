package com.pomogSpringBoot.testApp.common.coach;

import com.pomogSpringBoot.testApp.common.inrerface.Coach;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.printf("inside constructor: %s\n",  getClass().getSimpleName());
    }
    
    @Override
    public String workout() {
        return "Practice for 15 min. CricketCoach";
    }
}
