package com.project.demo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // Inject properties for: coach.name and team.name

    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    // Expose new endpoint for team info
    @GetMapping("/teaminfo")
    public String teamInfo() {
        return "coachName: " + coachName + ", teamName: " + teamName;
    }

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
