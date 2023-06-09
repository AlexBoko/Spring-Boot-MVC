package com.example.application.report.service;

import com.example.dto.EmployeeDto;
import com.example.application.report.model.Employee;
import org.modelmapper.ModelMapper;

public class EmployeeMapper {
    private static ModelMapper modelMapper = new ModelMapper();

    public static EmployeeDto toDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public static Employee toEntity(EmployeeDto employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }
}
