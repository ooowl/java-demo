// src/main/java/com/example/demo/aspect/LoggingAspect.java
package com.example.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(logExecutionTime)")  // 使用自定义注解作为切点
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();  // 执行目标方法

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
