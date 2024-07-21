package com.project.demo.rest;

import com.project.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    // Define @PostConstruct to load the student data ... only once!
    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();
        students.add(new Student("John", "Doe"));
        students.add(new Student("Jane", "Black"));
        students.add(new Student("Jack", "Brown"));
    }

    // Define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    // Define endpoint for "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // Just index into the list ... keep it simple for now

        // Check the studentId again list size
        if ((studentId < 0) || (studentId >= students.size())) {
            throw new StudentNotFoundException("Student id: " + studentId + " not found");
        }

        return students.get(studentId);

    }

}
