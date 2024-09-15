// src/main/java/com/example/demo/annotation/LogExecutionTime.java
package com.example.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  // 注解在运行时可用
@Target(ElementType.METHOD)          // 注解用于方法上
public @interface LogExecutionTime {
}
