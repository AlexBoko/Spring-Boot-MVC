package com.example.repository;

import com.example.dto.EmployeeDto;
import com.example.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    EmployeeDto createEmployee(EmployeeDto employee);

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);

    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDTO);

    void deleteEmployeeById(Long id);

    List<EmployeeDto> getEmployeesWithSalaryHigherThan(double salary);

    List<EmployeeDto> getEmployeesWithHighestSalary();

    List<EmployeeDto> getEmployeesByPosition(String positionName);

    Employee getEmployeeWithPosition(Long id);

    List<Employee> getEmployeesByPage(Pageable page);

    double findHighestSalary();

    List<EmployeeDto> findBySalaryGreaterThan(double salary);

    List<Employee> findByPositionName(String positionName);

    Employee findTopByOrderBySalaryDesc();

    Employee findByIdAndPositionId(Long id, Long positionId);

    Page<Employee> findAllByOrderByIdAsc(Pageable pageable);
}
