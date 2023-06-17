package com.example.application.report.repository;

import com.example.dto.EmployeeDto;
import com.example.application.report.model.Employee;
import com.example.application.report.model.Position;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final ModelMapper modelMapper;

    public EmployeeRepositoryImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employee) {
        Employee entity = modelMapper.map(employee, Employee.class);
        entityManager.persist(entity);
        return modelMapper.map(entity, EmployeeDto.class);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = entityManager.find(Employee.class, id);
        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setSalary(employee.getSalary());
            existingEmployee.setPosition(employee.getPosition());
            entityManager.merge(existingEmployee);
        }
        return existingEmployee;
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDTO) {
        Employee existingEmployee = entityManager.find(Employee.class, id);
        if (existingEmployee != null) {
            existingEmployee.setName(employeeDTO.getName());
            existingEmployee.setSalary(employeeDTO.getSalary());
            existingEmployee.setPosition(modelMapper.map(employeeDTO.getPosition(), Position.class));
            entityManager.merge(existingEmployee);
        }
        return employeeDTO;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            entityManager.remove(employee);
        }
    }

    @Override
    public List<EmployeeDto> getEmployeesWithSalaryHigherThan(double salary) {
        List<Employee> employees = entityManager.createQuery(
                        "SELECT e FROM Employee e WHERE e.salary > :salary", Employee.class)
                .setParameter("salary", salary)
                .getResultList();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getEmployeesWithHighestSalary() {
        double highestSalary = findHighestSalary();
        List<Employee> employees = entityManager.createQuery(
                        "SELECT e FROM Employee e WHERE e.salary = :highestSalary", Employee.class)
                .setParameter("highestSalary", highestSalary)
                .getResultList();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getEmployeesByPosition(String positionName) {
        List<Employee> employees = entityManager.createQuery(
                        "SELECT e FROM Employee e WHERE e.position.name = :positionName", Employee.class)
                .setParameter("positionName", positionName)
                .getResultList();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeWithPosition(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> getEmployeesByPage(Pageable page) {

        return null;
    }

    @Override
    public double findHighestSalary() {
        return 0.0;
    }

    @Override
    public List<EmployeeDto> findBySalaryGreaterThan(double salary) {
        List<Employee> employees = entityManager.createQuery(
                        "SELECT e FROM Employee e WHERE e.salary > :salary", Employee.class)
                .setParameter("salary", salary)
                .getResultList();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> findByPositionName(String positionName) {
        return null;
    }

    @Override
    public Employee findTopByOrderBySalaryDesc() {
        return null;
    }

    @Override
    public Employee findByIdAndPositionId(Long id, Long positionId) {
        return null;
    }

    @Override
    public Page<Employee> findAllByOrderByIdAsc(Pageable pageable) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public List<Employee> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Employee> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Employee employee) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends Employee> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Employee> S save(S s) {
        return null;
    }

    @Override
    public <S extends Employee> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Employee> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Employee> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Employee> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Employee> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Employee getOne(Long aLong) {
        return null;
    }

    @Override
    public Employee getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Employee> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Employee> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Employee> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Employee> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Employee> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Employee> boolean exists(Example<S> example) {
        return false;
    }
}