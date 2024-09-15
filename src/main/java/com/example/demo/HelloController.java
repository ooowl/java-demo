package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public ModelAndView sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("name", name);
        return modelAndView;
    }

}
