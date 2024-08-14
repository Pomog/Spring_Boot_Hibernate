package com.pomogSpringBoot.testApp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Order(1)
@Component
public class ApiAnalytics {
    
    @Before("AopExpressions.forAnyMethodFromTheDaoPackage()")
    public void beforeAnyDaoMethod(JoinPoint joinPoint) {
        System.out.println("\n =============> execution @Before advice for method form DAO");
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String argsAsString = Arrays.toString(joinPoint.getArgs());
        System.out.println(className+"."+methodName+"\n"+argsAsString);
    }
}
