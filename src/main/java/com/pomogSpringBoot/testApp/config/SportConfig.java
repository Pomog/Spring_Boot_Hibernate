package com.pomogSpringBoot.testApp.config;

import com.pomogSpringBoot.testApp.common.coach.SwimCoach;
import com.pomogSpringBoot.testApp.common.inrerface.Coach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean("aqua")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
