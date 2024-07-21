package com.project.demo.rest;

import com.project.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // Define endpoint for "/students" - return a list of students

    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("John", "Doe"));
        students.add(new Student("Jane", "Black"));
        students.add(new Student("Jack", "Brown"));

        return students;
    }

}
