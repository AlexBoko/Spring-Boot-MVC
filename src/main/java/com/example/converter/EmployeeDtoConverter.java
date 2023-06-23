package com.example.converter;

import com.example.dto.EmployeeDto;
import com.example.application.report.model.Employee;

public interface EmployeeDtoConverter {
    EmployeeDto convertToDto(Employee employee);

    Employee convertToEntity(EmployeeDto employeeDTO);
}
