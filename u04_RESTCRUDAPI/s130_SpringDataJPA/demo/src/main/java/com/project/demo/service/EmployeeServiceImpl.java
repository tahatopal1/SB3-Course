package com.project.demo.service;

import com.project.demo.dao.EmployeeRepository;
import com.project.demo.entity.Employee;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Employee with id " + id + " not found")
        );
    }

    @Override
//    @Transactional --> JpaRepository provides this functionality
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
