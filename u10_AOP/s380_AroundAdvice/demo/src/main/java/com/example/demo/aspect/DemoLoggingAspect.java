package com.example.demo.aspect;

import com.example.demo.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
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

    @Around("execution(* com.example.demo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint joinPoint) throws Throwable {

        // Print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        log.info("=====>>> Executing @Around on method: {}", method);

        // Get begin timestamp
        long startTime = System.currentTimeMillis();

        // Now, let's execute the method
        Object result = joinPoint.proceed();

        // Get end timestamp
        long endTime = System.currentTimeMillis();

        // Compute duration and display it
        long duration = endTime - startTime;
        log.info("Duration: {} seconds", duration / 1000.0);
        log.info("Fortune fetched during the advice: {}", result);
        return result;
    }

    // This will still work even though our method will throw an exception
    // ... and trigger the @AfterThrowing advice too
    // So we'll see both the advices have worked!
    @After("execution(* com.example.demo.dao.AccountDAO.findAccounts(..))")
    public void afterFindAccountAdvice(JoinPoint joinPoint) {
        // Print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        log.info("=====>>> Executing @After on method: {}", method);
    }

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
