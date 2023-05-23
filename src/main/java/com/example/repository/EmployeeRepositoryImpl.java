package com.example.repository;

import com.example.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private List<Employee> employees;

    public EmployeeRepositoryImpl() {
        employees = new ArrayList<>();
        employees.add(new Employee("Сергей", 50000));
        employees.add(new Employee("Александр", 60000));
        employees.add(new Employee("Антон", 45000));
    }

    @Override
    public List<Employee> findAll() {
        return employees;
    }
}
