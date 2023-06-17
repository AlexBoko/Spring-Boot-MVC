package com.example.application.report.controller;

import com.example.dto.EmployeeDto;
import com.example.application.report.service.EmployeeNotFoundException;
import com.example.application.report.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
@Validated
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Void> createEmployee(@Valid @RequestBody CreateEmployeeRequest createRequest) {
        employeeService.createEmployee(createRequest.getEmployeeDto());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody EmployeeDto employeeDTO) throws EmployeeNotFoundException {
        employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
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
    public ResponseEntity<Void> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    // Вспомогательный класс-запрос для создания сотрудника
    private static class CreateEmployeeRequest {
        @Valid
        private EmployeeDto employeeDto;

        public EmployeeDto getEmployeeDto() {
            return employeeDto;
        }

        public void setEmployeeDto(EmployeeDto employeeDto) {
            this.employeeDto = employeeDto;
        }
    }
}
