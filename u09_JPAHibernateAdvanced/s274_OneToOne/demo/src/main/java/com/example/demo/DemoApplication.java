package com.example.demo;

import com.example.demo.dao.AppDAO;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			createInstructor(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {

		// Create the instructor
		Instructor instructor = new Instructor("John", "Doe", "johnd@mail.co");

		// Create the instructor detail
		InstructorDetail instructorDetail = new InstructorDetail(
				"http://www.johndoe.com/youtube", "Coding"
		);

		// Associate the objects
		instructor.setInstructorDetail(instructorDetail);

		// Save the instructor
		//
		// NOTE: This will ALSO save the details object because of CascadeType.ALL
		log.info("Saving instructor: {}", instructor);
		appDAO.save(instructor);

		log.info("Done!");

	}

}
