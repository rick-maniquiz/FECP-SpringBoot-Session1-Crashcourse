package com.example.demo.myfirstapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController // Marks this class as a REST controller
public class HelloWorldController {

    @Value("${app.greeting.message}") // Inject value from application.properties
    private String customGreeting;

    @GetMapping("/hello")
    public String sayHello() {
        Scanner scanner = new Scanner(System.in);
        return customGreeting; // Now returns the custom message
    }

    @GetMapping("/greet/{name}") // This path now expects a dynamic 'name' part
    public String greetUser(@PathVariable String name) { // @PathVariable maps the URL part to this parameter
        return "Greetings, " + name + "!";
    }

    @GetMapping("/greeting")
    public String greetWithParam(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name){
        return "Hello, " + name + "!";
    }

    @GetMapping("/calculate/sum")
    public String sumNumbers(
            @RequestParam("num1") int num1,
            @RequestParam("num2") int num2){
        return "The sum of " + num1 + " and " + num2 + " is " + (num1 + num2);
    }

}

