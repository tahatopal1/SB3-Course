package com.project.dao;

import com.project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    // Add a method to sort by last name
    List<Employee> findAllByOrderByLastNameAsc();

}
