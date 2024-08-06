package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DemoLoggingAspect {

    // This is where we add all of our related advices for logging

    // Call any addAccount() method from any class (with public and void return type ofc)
    @Before("execution(public void addAccount())")
    public void beforeAddAccountAdvice() {
        log.info("=====>>> Executing @Before advice");
    }

}
