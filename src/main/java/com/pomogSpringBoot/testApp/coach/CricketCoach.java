package com.pomogSpringBoot.testApp.coach;

import com.pomogSpringBoot.testApp.inrerface.Coach;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {
    @Override
    public String workout() {
        return "Practice for 15 min. CricketCoach";
    }
}
