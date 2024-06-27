package com.project.springcoredemo.rest;

import com.project.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // Define a private field for the dependency
    private final Coach coach;


    // Define a constructor for dependency injection
    @Autowired
    public DemoController(@Qualifier("swimCoach") Coach coach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        this.coach = coach;
    }

    @GetMapping("/dailyworkout")
    public String dailyWorkout() {
        return coach.getDailyWorkout();
    }

}