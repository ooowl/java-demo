package com.example.demo;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements BeanNameAware {
    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("Bean name is: " + beanName);
    }

    public void printBeanName() {
        System.out.println("The bean name is: " + beanName);
    }
}