package com.example.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // If you only have pointcuts then @Aspect is optional
public class AopDeclarations {

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

}
