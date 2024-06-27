package com.project.util;

import org.springframework.stereotype.Component;

// This component won't be scanned by default as it's not under the same folder with the SpringBootApplication class.
// And this will cause an error and prevent the application from starting.
// We have to cover this one by using a manual component scanning with @ComponentScan annotation.

@Component
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes!!!";
    }

}
