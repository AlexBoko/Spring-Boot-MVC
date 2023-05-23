package com.example.controller;

import com.example.service.EmployeeService;
import com.example.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/employee")
@Getter
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/salary/sum")
    public double getTotalSalary() {
        return employeeService.getTotalSalary();
    }

    @GetMapping("/salary/min")
    public Employee getMinSalaryEmployee() {
        return employeeService.getMinSalaryEmployee();
    }

    @GetMapping("/salary/max")
    public Employee getMaxSalaryEmployee() {
        return employeeService.getMaxSalaryEmployee();
    }

    @GetMapping("/high-salary")
    public List<Employee> getHighSalaryEmployees() {
        return employeeService.getHighSalaryEmployees();
    }
}
