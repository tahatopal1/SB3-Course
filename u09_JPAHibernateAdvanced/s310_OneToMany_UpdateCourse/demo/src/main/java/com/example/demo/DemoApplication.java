package com.example.demo;

import com.example.demo.dao.AppDAO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
//			deleteInstructorDetail(appDAO);
//			createInstructorWithCourses(appDAO);
//			findInstructorWithCourses(appDAO);
//			findCoursesForInstructor(appDAO);
//			findInstructorWithCoursesJoinFetch(appDAO);
//			updateInstructor(appDAO);
			updateCourse(appDAO);
		};
	}

	private void updateCourse(AppDAO appDAO) {

		int id = 10;

		// Find the course
		log.info("Finding the course with id: {}", id);
		Course course = appDAO.findCourseById(10);

		// Update the course
		log.info("Updating the course with id: {}", id);
		course.setTitle("Enjoy the simple things.");

		appDAO.update(course);
		log.info("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {

		int id = 1;

		// Find the instructor
		log.info("Finding the instructor with id: {}", id);
		Instructor instructor = appDAO.findInstructorById(id);

		// Update the instructor
		log.info("Updating instructor with id: {}", id);
		instructor.setLastName("TESTER");

		appDAO.update(instructor);
		log.info("Done!");

	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;

		log.info("Finding instructor with id: {}", id);

		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);
		log.info("Found instructor: {}", instructor);
		log.info("Associated courses: {}", instructor.getCourses());
		log.info("Done!");
	}

	// S303 - We are separately having the courses list (lazy load ensured)
	// By doing that, we have prevented the LazyLoadInitialization error.
	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;
		log.info("Finding instructor id: {}", id);
		Instructor instructor = appDAO.findInstructorById(id);
		log.info("Instructor: {}", instructor);

		// Find courses for instructor
		log.info("Finding courses for instructor id: {}", id);
		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		instructor.setCourses(courses);

		log.info("Associated courses: {}", instructor.getCourses());
		log.info("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;
		log.info("Finding instructor id: {}", id);
		Instructor instructor = appDAO.findInstructorById(id);
		log.info("Instructor: {}", instructor);
		log.info("Associated courses: {}", instructor.getCourses());
		log.info("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// Create the instructor
		Instructor instructor = new Instructor("Susan", "Brown", "susanb@mail.co");

		// Create the instructor detail
		InstructorDetail instructorDetail = new InstructorDetail(
				"http://www.youtube.com", "Video Games"
		);

		// Associate the objects
		instructor.setInstructorDetail(instructorDetail);

		// Create some courses
		Course course1 = new Course("Air Guitar - The Ultimate Guide");
		Course course2 = new Course("Pinball Masterclass");

		// Add courses to instructor
		instructor.add(course1);
		instructor.add(course2);

		// Save the instructor
		//
		// NOTE: This will ALSO save courses
		// because of CascadeType.PERSIST
		//
		log.info("Saving instructor: {}", instructor);
		log.info("The courses: {}", instructor.getCourses());
		appDAO.save(instructor);

		log.info("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 10;
		log.info("Deleting instructor with id {}", id);
		appDAO.deleteInstructorDetailById(id);
		log.info("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id = 7;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
		log.info("Instructor Detail: {}", instructorDetail);
		log.info("Associated Instructor: {}", instructorDetail.getInstructor());
		log.info("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 6;
		log.info("Deleting instructor id: {}", id);
		appDAO.deleteInstructorById(id);
		log.info("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 6;
		log.info("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		log.info("Found instructor: {}", instructor);
		log.info("The assoociated instuctorDetail: {}", instructor.getInstructorDetail());
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
