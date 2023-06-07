package com.example.repository;

import com.example.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final Map<Long, Employee> employees;
    private final AtomicLong idCounter;

    public EmployeeRepositoryImpl() {
        employees = new HashMap<>();
        idCounter = new AtomicLong();
    }

    @Override
    public Employee save(Employee employee) {
        Long id = idCounter.incrementAndGet();
        employee.setId(id);
        employees.put(id, employee);
        return employee;
    }

    @Override
    public Employee findById(Long id) {
        return employees.get(id);
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public void deleteById(Long id) {
        employees.remove(id);
    }

    @Override
    public List<Employee> findBySalaryGreaterThan(double salary) {
        List<Employee> highSalaryEmployees = new ArrayList<>();
        for (Employee employee : employees.values()) {
            if (employee.getSalary() > salary) {
                highSalaryEmployees.add(employee);
            }
        }
        return highSalaryEmployees;
    }
}
