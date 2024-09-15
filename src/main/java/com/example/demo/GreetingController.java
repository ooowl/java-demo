// src/main/java/com/example/demo/controller/GreetingController.java
package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    // http://localhost:8080/greet?name=Wang
    @LogExecutionTime
    @GetMapping("/greet")
    public String greet(@RequestParam(value = "name", defaultValue = "World") String name) throws InterruptedException {
        Thread.sleep(2000);
        return "Hello " + name;
    }

}
