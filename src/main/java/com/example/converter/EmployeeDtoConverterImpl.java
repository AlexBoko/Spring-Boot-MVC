package com.example.converter;

import com.example.dto.EmployeeDto;
import com.example.application.report.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDtoConverterImpl implements EmployeeDtoConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeDtoConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto convertToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public Employee convertToEntity(EmployeeDto employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }
}
