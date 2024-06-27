package com.project.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // Expose "/" that returns "Hello World"

    @GetMapping("/")
    public String sayHello() {
        return "Hello World";
    }

    // Expose a new endpoint for work the devtool reloading out
    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "Run for it!";
    }

}
