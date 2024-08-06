package com.project.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    // Need a controller method to show initial HTML form

    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    // Need a controller method to process the HTML form

//    @RequestMapping("/processForm")
//    public String processForm() {
//        return "helloworld";
//    }

    // Need a controller method to read form data
    // and add data to the model

    @RequestMapping("/processForm")
    public String letsShoutDude(@RequestParam("studentName") String name, Model model) {

        // Convert the data to all caps
        name = name.toUpperCase();

        // Create the message
        String result = "Hey My Friend " + name;

        // Add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }

}
