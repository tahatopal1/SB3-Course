package com.project.cruddemo;

import com.project.cruddemo.dao.StudentDAO;
import com.project.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	// Executed after the Spring Beans have been loaded
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
//			createStudent(studentDAO);
//			createMultipleStudents(studentDAO);
			readStudent(studentDAO);
		};
	}

	private void readStudent(StudentDAO studentDAO) {
		// Create a student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@mail.co");

		// Save the student
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// Display id of the saved student
		int id = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + id);

		// Retrieve student based on the id: Primary Key
		System.out.println("Retrieving student with id " + id);
		Student myStudent = studentDAO.findById(id);

		// Display student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		// Create the student object
		System.out.println("Creating 3 student objects...");
		Student tempStudent1 = new Student("John", "Doe", "paul@mail.co");
		Student tempStudent2 = new Student("Mary", "Public", "mary@mail.co");
		Student tempStudent3 = new Student("Jake", "Blue", "jake@mail.co");

		// Save the student object
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

		// Display id of the saved students
		// Will expect consecutive id values
		System.out.println("Saved student. Generated id: " + tempStudent1.getId());
		System.out.println("Saved student. Generated id: " + tempStudent2.getId());
		System.out.println("Saved student. Generated id: " + tempStudent3.getId());

	}

	private void createStudent(StudentDAO studentDAO) {

		// Create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul@mail.co");

		// Save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// Display if of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());

	}

}
