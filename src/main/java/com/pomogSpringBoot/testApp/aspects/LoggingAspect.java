package com.pomogSpringBoot.testApp.aspects;

import com.pomogSpringBoot.testApp.entity.glassware.LabGlassware;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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
    public void afterHappyAnyMethod(JoinPoint joinPoint, List<?> results) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(" executed \n" + methodName + ": " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("returning: " + results.size() + " entities");
        
        // modifying, post-process the return results
        if (!results.isEmpty() && results.get(0).getClass() == LabGlassware.class) {
            LabGlassware labGlassware = (LabGlassware) results.get(0);
            labGlassware.setMaterial("concrete");
        }
    }
    
    @AfterThrowing(
            pointcut = "AopExpressions.forAnyMethodInTheProject()",
            throwing = "exc"
    )
    public void errorInFindMethod(JoinPoint joinPoint, Throwable exc) {
        String methodName = joinPoint.getSignature().getName();
        String signature = String.valueOf(joinPoint.getSignature());
        System.out.println("method -> " + methodName + " returns exception");
        System.out.println(signature);
        System.out.println(exc.getMessage());
    }
    
    @After("AopExpressions.forAnyMethodInTheProject()")
    public void afterAdvice(JoinPoint joinPoint) {
        String signature = String.valueOf(joinPoint.getSignature());
        System.out.println("Method was executed");
        System.out.println(signature);
    }
    
    @Around("AopExpressions.forFind()")
    public Object showExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String signature = String.valueOf(proceedingJoinPoint.getSignature());
        System.out.println("\n Executing @Around on: " + signature);
        
        long start = System.currentTimeMillis();

        try {
            Object result = proceedingJoinPoint.proceed();
            
            long end = System.currentTimeMillis();
            long duration = end - start;
            
            System.out.println("\nDuration is: " + duration + " ms\n");
            System.out.println("RETURNED: " + result);
            return result;
        } catch (Exception exc) {
            System.out.println("Exception caught by @Around advice: " + exc + "\n" + exc.getMessage());
            throw exc;
        }
    }
    
    
}
