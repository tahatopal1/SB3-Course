package com.project.demo.rest;

import com.project.demo.entity.Employee;
import com.project.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    // Expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new RuntimeException("Employee with id " + id + " not found");
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {

        // Also just in case they pass an id in JSON ... set id to 0
        // This is to force a save of new item ... instead of update
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) {
        Employee tempEmployee = employeeService.findById(id);
        if (tempEmployee == null) {
            throw new RuntimeException("Employee with id " + id + " not found");
        }
        employeeService.deleteById(id);
    }


}
