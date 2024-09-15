package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    // http://localhost:8080/greet?name=Wang
    @GetMapping("/greet")
    public String greet(@RequestParam(value = "name", defaultValue = "World") String name) throws InterruptedException {
        return "Hello " + name;
    }

}
