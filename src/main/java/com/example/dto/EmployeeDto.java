package com.example.dto;

public class EmployeeDto {
    private Long id;
    private String name;
    private double salary;
    private PositionDto position;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String name, double salary, PositionDto position) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }
}
