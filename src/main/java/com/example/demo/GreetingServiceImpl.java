// src/main/java/com/example/demo/service/GreetingServiceImpl.java
package com.example.demo;

import org.springframework.stereotype.Service;

@Service  // 将这个类标记为 Spring 的一个 Bean
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}
