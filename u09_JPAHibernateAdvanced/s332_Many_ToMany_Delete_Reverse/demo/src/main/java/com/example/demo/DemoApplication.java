package com.example.demo;

import com.example.demo.dao.AppDAO;
import com.example.demo.entity.*;
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
//			createCourseAndStudents(appDAO);
//			findCourseAndStudents(appDAO);
//			findStudentAndCourses(appDAO);
//			addMoreCoursesForStudent(appDAO);
//			deleteCourse(appDAO);
			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {

		int id = 1;
		log.info("Deleting student with id {}", id);

		appDAO.deleteStudentById(1);
		log.info("Done!");

	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {

		int id = 2;
		Student student = appDAO.findStudentAndCoursesByStudentId(id);

		// Create more courses
		Course course1 = new Course("Rubik's Cube - How to Speed Cube");
		Course course2 = new Course("Atari 2600 - Game Development");

		// Add courses to the student
		student.addCourse(course1);
		student.addCourse(course2);

		log.info("Updating the student: {}", student);
		log.info("Associated courses: {}", student.getCourses());

		appDAO.update(student);
	}

	private void findStudentAndCourses(AppDAO appDAO) {

		int id = 2;

		Student student = appDAO.findStudentAndCoursesByStudentId(id);
		log.info("Loaded student: {}", student);
		log.info("Courses: {}", student.getCourses());
		log.info("Done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {

		int id = 10;

		Course course = appDAO.findCourseAndStudentsByCourseId(id);
		log.info("Loaded course: {}", course);
		log.info("Students: " + course.getStudents());
		log.info("Done!");

	}

	private void createCourseAndStudents(AppDAO appDAO) {

		// Create a course
		Course course = new Course("Pacman - How To Score One Million Points");

		// Create the students
		Student student1 = new Student("John", "Doe", "johnd@mail.co");
		Student student2 = new Student("Mary", "Jane", "maryj@mail.co");

		// Add students to the course
		course.addStudent(student1);
		course.addStudent(student2);

		// Save the course and associated students
		log.info("Saving course: {}", course);
		log.info("Associated students: {}", course.getStudents());

		appDAO.save(course);
		log.info("Done!");

	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		log.info("Deleting the course with id: {}", id); // Will cascade delete
		appDAO.deleteCourseById(id);
		log.info("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// Get the course and reviews
		int id = 10;
		Course course = appDAO.findCourseAndReviewsByCourseId(id);

		// Print the course
		log.info("Course: {}", course);

		// Print the reviews
		log.info("Reviews: {}", course.getReviews());

	}


	private void createCourseAndReviews(AppDAO appDAO) {

		// Create a course
		Course course = new Course("Pacman - How to Score One Million Points");

		// Add some reviews
		course.addReview(new Review("Creat course ... loved it!!"));
		course.addReview(new Review("Cool course, job well done."));
		course.addReview(new Review("What a dumb course, you are an idiot!"));

		// Save the course ... and leverage the cascade all
		appDAO.save(course);

		log.info("Saving the course...");
		log.info("Course: {}", course);
		log.info("Reviews: {}", course.getReviews());

	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10;
		log.info("Deleting the course with id: {}", id);
		appDAO.deleteCourseById(id);
		log.info("Done!");
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
		int id = 1;
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
