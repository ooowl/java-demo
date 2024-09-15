// src/main/java/com/example/demo/aspect/LoggingAspect.java
package com.example.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect  // 声明这是一个切面类
@Component  // 将其交给 Spring 管理
public class LoggingAspect1 {

//    注释掉，以测试自定义注解，替代相同的功能
//    @Around("execution(* com.example.demo.GreetingController.greet(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();  // 执行目标方法

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
