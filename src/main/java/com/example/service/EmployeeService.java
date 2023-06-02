package com.example.service;

import com.example.repository.EmployeeRepository;
import com.example.model.Employee;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public double getTotalSalary() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public Employee getMinSalaryEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    public Employee getMaxSalaryEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    public List<Employee> getHighSalaryEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        double averageSalary = getTotalSalary() / employees.size();
        return employees.stream()
                .filter(employee -> employee.getSalary() > averageSalary)
                .toList();
    }
}

