package com.pomogSpringBoot.testApp.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {
    
    @Pointcut("execution(* com.pomogSpringBoot.testApp.*.*.* (..))")
    public void forAnyMethodInTheProject(){}
    
    @Pointcut("execution(* com.pomogSpringBoot.testApp.dao.*.* (..))")
    public void forAnyMethodFromTheDaoPackage(){}
    
    @Pointcut("execution(* com.pomogSpringBoot.testApp.*.*.find* (..))")
    public void forAnyFind(){}
    
    @Pointcut("execution(* com.pomogSpringBoot.testApp.*.*.delete* (..))")
    public void forDeleting(){}
}
