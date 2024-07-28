package com.pomogSpringBoot.testApp.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(public com.pomogSpringBoot.testApp.entity.glassware.LabGlassware save(com.pomogSpringBoot.testApp.entity.glassware.LabGlassware))")
    public void beforeSaveAdvice() {
        System.out.println("\n =============> execution @Before advice");
    }
    
    @Before("execution(* save(..))")
    public void beforeAnySaveAdvice() {
        System.out.println("\n =============> execution @Before advice for any save method");
    }
    
    // TODO: it is not worked
    /*
    not a Spring-managed bean MAYBE
     */
    @Before("execution(* checkBefore*(..))")
    public void beforeAnyMethodWithLong() {
        System.out.println("\n =============> execution @Before advice for any method with one param Long type");
    }
    
    @Before("execution(* filterChain*(..))")
    public void beforeFilterChain() {
        System.out.println("\n =============> execution @Before advice for the filterChain");
    }
    
    // TODO: it is not worked, Broad pointcuts gives error
//    @Before("execution(* *())")
//    public void transform (){
//        System.out.println("\n =============> execution @Before advice for any transform method");
//    }
    
    // Broad pointcuts for my project
    @Before("execution(* com.pomogSpringBoot.testApp.*.* (..))")
    public void beforeAnyMethod() {
        System.out.println("\n =============> execution @Before advice for any method");
    }
}
