package com.example.converter;

import com.example.dto.EmployeeDto;
import com.example.model.Employee;

public interface EmployeeDtoConverter {
    EmployeeDto convertToDto(Employee employee);

    Employee convertToEntity(EmployeeDto employeeDTO);
}
