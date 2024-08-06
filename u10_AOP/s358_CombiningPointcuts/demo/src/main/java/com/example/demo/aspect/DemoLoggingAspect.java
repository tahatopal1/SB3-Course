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

    // Create a pointcut for getter methods
    @Pointcut("execution(* com.example.demo.dao.*.get*(..))")
    public void getter() {}

    // Create a pointcut for setter methods
    @Pointcut("execution(* com.example.demo.dao.*.set*(..))")
    public void setter() {}

    // Create pointcut: include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() ||  setter())")
    public void forDaoPackageNoGetterSetter() {}

    // The following advices will be expected to be called

    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        log.info("=====>>> Executing @Before advice");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        log.info("=====>>> Performing API analytics");
    }

}
