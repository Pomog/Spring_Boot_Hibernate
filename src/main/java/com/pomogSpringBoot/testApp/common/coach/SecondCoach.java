package com.pomogSpringBoot.testApp.common.coach;

import com.pomogSpringBoot.testApp.common.inrerface.Coach;
import org.springframework.stereotype.Component;

@Component
public class SecondCoach implements Coach {
    @Override
    public String workout() {
        return "Practice for 25 min. SecondCoach";
    }
}
