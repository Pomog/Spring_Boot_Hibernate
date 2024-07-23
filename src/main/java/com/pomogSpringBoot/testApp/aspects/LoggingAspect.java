package com.pomogSpringBoot.testApp.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(public com.pomogSpringBoot.testApp.entity.glassware.LabGlassware save(com.pomogSpringBoot.testApp.entity.glassware.LabGlassware))")
    public void beforeSaveAdvice(){
        System.out.println("\n =============> execution @Before advice");
    }
    
    @Before("execution(* save(*))")
    public void beforeAnySaveAdvice(){
        System.out.println("\n =============> execution @Before advice for any save method");
    }
}
