package com.project.controller;

import com.project.entity.Employee;
import com.project.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    // Add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model model) {

        // Get the employees from db
        List<Employee> employees = employeeService.findAll();

        // Add to the spring model
        model.addAttribute("employees", employees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {

        // Save the employee
        employeeService.save(employee);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model) {

        // Get the employee from the service
        Employee employee = employeeService.findById(id);

        // Set employee in the model to prepopulate the form
        model.addAttribute("employee", employee);

        // Send over to the form
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }

}
