package com.example.controller;

import com.example.dto.EmployeeDto;
import com.example.service.EmployeeNotFoundException;
import com.example.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/")
    public void createEmployee(@Valid @RequestBody EmployeeDto employeeDTO) {
        employeeService.createEmployee(employeeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        EmployeeDto employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody EmployeeDto employeeDTO) throws EmployeeNotFoundException {
        employeeService.updateEmployee(id, employeeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/salaryHigherThan")
    public List<EmployeeDto> getEmployeesWithSalaryHigherThan(@RequestParam("salary") double salary) {
        return employeeService.getEmployeesWithSalaryHigherThan(salary);
    }

    @GetMapping("/withHighestSalary")
    public List<EmployeeDto> getEmployeesWithHighestSalary() {
        return employeeService.getEmployeesWithHighestSalary();
    }

    @GetMapping("/position")
    public List<EmployeeDto> getEmployeesByPosition(@RequestParam(value = "position", required = false) String positionName) {
        return employeeService.getEmployeesByPosition(positionName);
    }

    @GetMapping("/{id}/fullInfo")
    public EmployeeDto getEmployeeWithPosition(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        return employeeService.getEmployeeWithPosition(id);
    }

    @GetMapping("/page/{page}")
    public List<EmployeeDto> getEmployeesByPage(@PathVariable("page") int page) {
        return employeeService.getEmployeesByPage(page);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
