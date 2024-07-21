package com.project.demo.dao;

import com.project.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // That's it ... no need to write any code

}
