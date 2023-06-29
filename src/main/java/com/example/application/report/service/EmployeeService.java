package com.example.application.report.service;

import com.example.application.report.repository.EmployeeRepository;
import com.example.application.report.model.Employee;
import com.example.application.report.service.EmployeeMapper;
import com.example.application.report.service.EmployeeNotFoundException;
import com.example.dto.EmployeeDto;
import com.example.dto.PositionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDto> getEmployeesWithHighestSalary() {
        // Получение наивысшей зарплаты
        double highestSalary = employeeRepository.findHighestSalary();
        // Получение сотрудников с зарплатой выше наивысшей
        return employeeRepository.findBySalaryGreaterThan(highestSalary);
    }

    public List<EmployeeDto> getEmployeesByPosition(String position) {
        // Получение сотрудников по должности
        return employeeRepository.getEmployeesByPosition(position);
    }

    public EmployeeDto getEmployeeFullInfo(Long id) throws EmployeeNotFoundException {
        // Получение полной информации о сотруднике по идентификатору
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }

        // Создание DTO для должности
        PositionDto positionDto = new PositionDto();
        positionDto.setId(employee.getPosition().getId());
        positionDto.setName(employee.getPosition().getName());

        // Создание DTO для сотрудника
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setPosition(positionDto);

        return employeeDto;
    }

    public List<EmployeeDto> getEmployeesWithPagination(int page) {
        // Размер страницы
        int pageSize = 10;
        // Создание объекта для постраничной навигации
        Pageable pageable = PageRequest.of(page, pageSize);
        // Получение страницы сотрудников
        Page<Employee> employeePage = (Page<Employee>) employeeRepository.getEmployeesByPage(pageable);
        // Создание списка DTO для сотрудников
        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        // Проход по содержимому страницы и создание DTO для каждого сотрудника
        for (Employee employee : employeePage.getContent()) {
            PositionDto positionDto = new PositionDto();
            positionDto.setId(employee.getPosition().getId());
            positionDto.setName(employee.getPosition().getName());

            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setSalary(employee.getSalary());
            employeeDto.setPosition(positionDto);

            employeeDtoList.add(employeeDto);
        }

        return employeeDtoList;
    }

    public EmployeeDto createEmployee(EmployeeDto employee) {
        // Создание сотрудника
        return employeeRepository.createEmployee(employee);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto employee) throws EmployeeNotFoundException {
        // Получение существующего сотрудника
        Employee existingEmployee = employeeRepository.getEmployeeById(id);
        if (existingEmployee == null) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        // Обновление информации о сотруднике
        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        // Обновление сотрудника в репозитории
        employeeRepository.updateEmployee(id, existingEmployee);
        return employee;
    }

    public abstract void createEmployee(Employee employee);

    public EmployeeDto getEmployeeById(Long id) throws EmployeeNotFoundException {
        // Получение сотрудника по идентификатору
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        // Преобразование сотрудника в DTO
        return EmployeeMapper.toDto(employee);
    }

    public void deleteEmployeeById(Long id) throws EmployeeNotFoundException {
        // Получение сотрудника по идентификатору
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        // Удаление сотрудника
        employeeRepository.deleteEmployeeById(id);
    }

    public List<EmployeeDto> getEmployeesWithSalaryHigherThan(double salary) {
        // Получение сотрудников с зарплатой выше указанной
        return employeeRepository.getEmployeesWithSalaryHigherThan(salary);
    }

    public EmployeeDto getEmployeeWithPosition(Long id) throws EmployeeNotFoundException {
        // Получение сотрудника с должностью по идентификатору
        Employee employee = employeeRepository.getEmployeeWithPosition(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        // Преобразование сотрудника в DTO
        return EmployeeMapper.toDto(employee);
    }

    public List<EmployeeDto> getEmployeesByPage(int page) {
        // Размер страницы
        int pageSize = 10;
        // Создание объекта для постраничной навигации
        Pageable pageable = PageRequest.of(page, pageSize);
        // Получение страницы сотрудников
        Page<Employee> employeePage = (Page<Employee>) employeeRepository.getEmployeesByPage(pageable);
        // Создание списка DTO для сотрудников
        List<EmployeeDto> employeeDtoList = new ArrayList<>();


        for (Employee employee : employeePage.getContent()) {
            PositionDto positionDto = new PositionDto();
            positionDto.setId(employee.getPosition().getId());
            positionDto.setName(employee.getPosition().getName());

            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setSalary(employee.getSalary());
            employeeDto.setPosition(positionDto);

            employeeDtoList.add(employeeDto);
        }

        return employeeDtoList;
    }
}

