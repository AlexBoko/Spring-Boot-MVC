package com.example.service;

import com.example.repository.EmployeeRepository;
import com.example.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id);
        if (existingEmployee == null) {
            // Обработка ошибки, если сотрудник не найден
        }
        // Обновляем данные сотрудника
        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());

        // Сохраняем обновленного сотрудника
        return employeeRepository.save(existingEmployee);
    }

    public Employee getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            // Обработка ошибки, если сотрудник не найден
        }
        return employee;
    }

    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            // Обработка ошибки, если сотрудник не найден
        }
        employeeRepository.deleteById(id);
    }

    public List<Employee> getEmployeesWithSalaryHigherThan(double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }
}