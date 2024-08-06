package com.pomogSpringBoot.testApp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

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
        System.out.println(joinPoint.getStaticPart());
        System.out.println(className + "." + methodName);
        System.out.println(Arrays.toString(joinPoint.getArgs()));
        
        /*
        try {
            // Get the class object
            Class<?> clazz = joinPoint.getTarget().getClass();
            
            // Find the method with the matching name and parameter types
            Method method = Arrays.stream(clazz.getMethods())
                    .filter(m -> m.getName().equals(methodName) &&
                            Arrays.equals(m.getParameterTypes(), Arrays.stream(joinPoint.getArgs())
                                    .map(Object::getClass).toArray(Class<?>[]::new)))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchMethodException("Method not found: " + methodName));
            
            // Get the return type of the method
            Class<?> returnType = method.getReturnType();
            System.out.println("Return type: " + returnType.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         */
    }
    
    @Before("AopExpressions.forAnyMethodFromTheDaoPackage() && !AopExpressions.forAnyFind()")
    public void beforeAnyDaoMethodNotFind(JoinPoint joinPoint) {
        System.out.println("\n =============> execution @Before advice for method form DAO, exclude FIND");
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String argsAsString = Arrays.toString(joinPoint.getArgs());
        System.out.println(className + "." + methodName + "\n" + argsAsString);
    }
    
    /*
    The advice not work when returning type not a List
     */
    @AfterReturning(
            pointcut = "AopExpressions.forFind()",
            returning = "results")
    public void afterHappyAnyMethod(JoinPoint joinPoint, List<?> results)  {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(" executed \n" + methodName + ": " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("returning: " + results.size() + " entities");
    }
    
    
}
