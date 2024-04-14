package com.pomogSpringBoot.testApp.common.coach;

import com.pomogSpringBoot.testApp.common.inrerface.Coach;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Primary
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CricketCoach implements Coach {
    private String coachName;
    public CricketCoach() {
        System.out.printf("inside constructor: %s\n",  getClass().getSimpleName());
    }
    
    @Override
    public String workout() {
        return "Practice for 15 min. CricketCoach";
    }
    
    @PostConstruct
    public void postConstructFunction(){
        this.coachName = "John Doe";
        System.out.println("postConstructFunction: " + getClass().getSimpleName());
    }
    
    @PreDestroy
    public void preDestroyFunction(){
        System.out.println("preDestroyFunction: " + getClass().getSimpleName());
    }
    
    public String getCoachName() {
        return coachName;
    }
}
