package com.example.repository;

import com.example.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    Employee save(Employee employee);
    Employee findById(Long id);
    List<Employee> findAll();
    void deleteById(Long id);
    List<Employee> findBySalaryGreaterThan(double salary);
}
