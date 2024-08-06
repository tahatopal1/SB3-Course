package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(1)
public class MyCloudLogAspect {

    @Before("com.example.demo.aspect.AopDeclarations.forDaoPackageNoGetterSetter()")
    public void logToCloud() {
        log.info("=====>>> Logging to Cloud");
    }


}
