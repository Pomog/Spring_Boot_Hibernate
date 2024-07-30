package com.pomogSpringBoot.testApp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* com.pomogSpringBoot.testApp.*.*.* (..))")
    private void forAnyMethodInTheProject(){}
    
    // Broad pointcuts for my project
    @Before("forAnyMethodInTheProject()")
    public void beforeAnyMethod(JoinPoint joinPoint) {
        System.out.println("\n =============> execution @Before advice for method");
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println(className+"."+methodName);
    }

}
