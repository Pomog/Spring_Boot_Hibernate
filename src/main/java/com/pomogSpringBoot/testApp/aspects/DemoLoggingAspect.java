package com.pomogSpringBoot.testApp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Order(0)
@Component
public class DemoLoggingAspect {
    private final Logger logger = Logger.getLogger(getClass().getName());
    
    @Before("AopExpressions.forAnyMethodFromTheDaoPackage()")
    public void before (JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        logger.info("-----------> calling DAO method: " + method);
        
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("-----------> args: " + args);
        
        String location = joinPoint.getSourceLocation().toString();
        logger.info("-----------> location: " + location);
    }
    
    @AfterReturning(
            pointcut = "AopExpressions.forAnyMethodFromTheDaoPackage()",
            returning = "result"
    )
    public void after (JoinPoint joinPoint, Object result){
        String method = joinPoint.getSignature().toShortString();
        logger.info("-----------> after calling DAO method: " + method);
        
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("-----------> with args: " + args);
        
        logger.info("-----------> result is: " + result);
    }
}
