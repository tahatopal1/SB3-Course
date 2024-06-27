package com.project.springcoredemo.common;

// This time we're not using @Component on purpose as we'll create a bean with @Bean annotation
public class SwimCoach implements Coach{

    public SwimCoach() {
        System.out.println("In constructor: " + this.getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters as a warm up";
    }
}
