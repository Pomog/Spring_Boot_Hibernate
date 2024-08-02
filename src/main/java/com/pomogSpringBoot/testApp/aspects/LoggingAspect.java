package com.pomogSpringBoot.testApp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* com.pomogSpringBoot.testApp.*.*.* (..))")
    private void forAnyMethodInTheProject(){}
    
    @Pointcut("execution(* com.pomogSpringBoot.testApp.dao.*.* (..))")
    private void forAnyMethodFromTheDaoPackage(){}
    
    @Pointcut("execution(* com.pomogSpringBoot.testApp.*.*.find* (..))")
    private void forAnyFind(){}
    
    // Broad pointcuts for my project
    @Before("forAnyMethodInTheProject()")
    public void beforeAnyMethod(JoinPoint joinPoint) {
        System.out.println("\n =============> execution @Before advice for ALL methods");
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println(className+"."+methodName);
    }
    
    @Before("forAnyMethodFromTheDaoPackage() && !forAnyFind()")
    public void beforeAnyDaoMethod(JoinPoint joinPoint) {
        System.out.println("\n =============> execution @Before advice for method form DAO, exclude FIND");
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String argsAsString = Arrays.toString(joinPoint.getArgs());
        System.out.println(className+"."+methodName+"\n"+argsAsString);
    }

}
