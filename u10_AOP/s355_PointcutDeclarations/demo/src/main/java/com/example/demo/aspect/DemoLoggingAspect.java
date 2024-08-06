package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DemoLoggingAspect {

    @Pointcut("execution(* com.example.demo.dao.*.*(..))")
    public void forDaoPackage() {}

    // The following advices will be expected to be called

    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice() {
        log.info("=====>>> Executing @Before advice");
    }

    @Before("forDaoPackage()")
    public void performApiAnalytics() {
        log.info("=====>>> Performing API analytics");
    }

}
