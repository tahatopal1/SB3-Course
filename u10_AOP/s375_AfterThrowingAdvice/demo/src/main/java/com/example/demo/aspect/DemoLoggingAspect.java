package com.example.demo.aspect;

import com.example.demo.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Slf4j
@Order(2)
public class DemoLoggingAspect {

    @AfterThrowing(
            pointcut = "execution(* com.example.demo.dao.AccountDAO.findAccounts(..))",
            throwing = "ex")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable ex) {

        // Print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        log.info("=====>>> Executing @AfterThrowing on method: {}", method);

        // Log the exception
        log.info("=====>>> Exception is: {}", ex.toString());

    }

    @AfterReturning(
            pointcut = "execution(* com.example.demo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> result) {

        // Print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        log.info("=====>>> Executing @AfterReturning on method: {}", method);

        // Print out the results of the method call
        log.info("=====>>> Result is: {}", result);

        // Let's post-process the data ... let's modify it!
        // Convert the account names to uppercase
        convertAccountNamesToUppercase(result);

    }

    private void convertAccountNamesToUppercase(List<Account> result) {
        for (Account account : result) {
            String name = account.getName().toUpperCase();
            account.setName(name);
        }
    }

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
