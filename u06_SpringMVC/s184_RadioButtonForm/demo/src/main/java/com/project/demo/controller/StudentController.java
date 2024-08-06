package com.project.demo.controller;

import com.project.demo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class StudentController {

    // Added after we wanted to have the countries data dynamically
    @Value("${countries}")
    private List<String> countries;

    // Added after we wanted to have the countries data dynamically
    @Value("${languages}")
    private List<String> languages;


    @GetMapping("/showStudentForm")
    public String showForm(Model model) {

        // Create a student object
        Student student = new Student();

        // Add student object to the model
        model.addAttribute("student", student);

        // Add the list of countries to the model
        model.addAttribute("countries", countries);

        // Add the list of languages to the model
        model.addAttribute("languages", languages);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student student) {

        // Log the input data
        log.info("The Student: " + student.toString());

        return "student-confirmation";
    }

}
