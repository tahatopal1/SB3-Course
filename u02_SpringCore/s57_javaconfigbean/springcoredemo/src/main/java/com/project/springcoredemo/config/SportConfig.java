package com.project.springcoredemo.config;

import com.project.springcoredemo.common.Coach;
import com.project.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean // Bean id is "swimCoach" by default. But you can put a custom name like below.
//    @Bean("swimCoach")
    public Coach swimCoach() {
        return new SwimCoach();
    }

}
