package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(3)
public class MyApiAnalyticsAspect {

    @Before("com.example.demo.aspect.AopDeclarations.forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        log.info("=====>>> Performing API analytics");
    }


}
