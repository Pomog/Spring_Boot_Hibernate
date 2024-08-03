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
public class LoggingAspect {
    
    // Broad pointcuts for my project
    @Before("AopExpressions.forAnyMethodInTheProject()")
    public void beforeAnyMethod(JoinPoint joinPoint) {
        System.out.println("\n =============> execution @Before advice for ALL methods");
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println(className+"."+methodName);
    }
    
    @Before("AopExpressions.forAnyMethodFromTheDaoPackage() && !AopExpressions.forAnyFind()")
    public void beforeAnyDaoMethodNotFind(JoinPoint joinPoint) {
        System.out.println("\n =============> execution @Before advice for method form DAO, exclude FIND");
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String argsAsString = Arrays.toString(joinPoint.getArgs());
        System.out.println(className+"."+methodName+"\n"+argsAsString);
    }
    


}
