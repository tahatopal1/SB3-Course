package com.example.demo.aspect;

import com.example.demo.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(2)
public class DemoLoggingAspect {

    @Before("com.example.demo.aspect.AopDeclarations.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        log.info("=====>>> Executing @Before advice");

        // Display the method signature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("Method: {}", signature);

        // Display method arguments
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {

            log.info("Arg: {}", arg);

            if (arg instanceof Account) {
                Account account = (Account) arg;
                log.info("Account name: {}", account.getName());
                log.info("Account level: {}", account.getLevel());
            }

        }

    }

}
