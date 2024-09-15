package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String createUser(@RequestParam String name, @RequestParam String email) {
        userService.createUser(name, email);
        return "User created successfully!";
    }

    @GetMapping("/createWithError")
    public String createUserWithException(@RequestParam String name, @RequestParam String email) {
        try {
            userService.createUserWithException(name, email);
        } catch (Exception e) {
            return "Transaction rolled back due to: " + e.getMessage();
        }
        return "This won't be shown as transaction will rollback.";
    }
}
