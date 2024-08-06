package com.project.aspect;

import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {

    // Setup logger
    private Logger logger = Logger.getLogger(this.getClass().getName());

    // Setup pointcut declarations
    @Pointcut("execution(* com.project.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.project.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.project.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    // Add @Before advice
    @Before("forAppFlow()")
    private void before(JoinPoint joinPoint) {

        // Display method we are calling
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @Before: " + method);

        // Display the arguments of the method
        // With a workaround :)
        Arrays.stream(joinPoint.getArgs())
                .map(Objects::toString)
                .filter(arg -> !arg.equals("{}"))
                .forEach(arg -> logger.info("=====>> Argument: " + arg));

    }

    // Add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    private void afterReturning(JoinPoint joinPoint, Object result) {

        // Display method we are returning from
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @AfterReturning: " + method);

        // Display data returned
        logger.info("=====>> in result: " + result);

    }
}
