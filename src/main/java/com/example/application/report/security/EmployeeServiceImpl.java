package com.example.application.report.security;

import com.example.application.report.model.Employee;
import com.example.application.report.repository.EmployeeRepository;
import com.example.application.report.service.EmployeeService;
import com.example.dto.EmployeeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void createEmployee(Employee employee) {
        logger.info("The method createEmployee has been invoked");
        try {

            logger.debug("Employee created successfully: {}", employee);
        } catch (Exception e) {
            logger.error("Error occurred while creating employee: {}", employee, e);
            throw new RuntimeException("Failed to create employee", e);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        logger.info("The method getEmployeeById has been invoked");
        try {

            Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
            logger.debug("Employee retrieved successfully by ID: {}", id);
            return convertToDto(employee);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving employee by ID: {}", id, e);
            throw new RuntimeException("Failed to retrieve employee", e);
        }
    }

    private EmployeeDto convertToDto(Employee employee) {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());

        return employeeDto;
    }
}
