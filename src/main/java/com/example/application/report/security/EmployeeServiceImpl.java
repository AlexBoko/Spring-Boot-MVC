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

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super(employeeRepository);
    }

    @Override
    public void createEmployee(Employee employee) {
        logger.info("Was invoked method for create employee: {}", employee);
        try {
            // Логика создания сотрудника
            logger.debug("Successfully created employee: {}", employee);
        } catch (Exception e) {
            logger.error("Failed to create employee: {}", employee, e);
            throw new RuntimeException("Failed to create employee", e);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        logger.info("Was invoked method to get employee by id: {}", id);
        try {
            // Логика получения сотрудника по id
            Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
            logger.debug("Successfully retrieved employee with id: {}", id);
            return convertToDto(employee);
        } catch (Exception e) {
            logger.error("Failed to get employee with id: {}", id, e);
            throw new RuntimeException("Failed to get employee", e);
        }
    }

    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());

        return employeeDto;
    }
}
