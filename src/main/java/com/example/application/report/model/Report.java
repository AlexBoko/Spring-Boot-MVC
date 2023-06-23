package com.example.application.report.model;

import javax.persistence.*;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department")
    private String department;

    @Column(name = "employee_count")
    private int employeeCount;

    @Column(name = "max_salary")
    private double maxSalary;

    @Column(name = "min_salary")
    private double minSalary;

    @Column(name = "average_salary")
    private double averageSalary;

    @Column(name = "file_path")
    private String filePath;

    // Конструкторы, геттеры и сеттеры

    public Report() {
    }

    public Report(String department, int employeeCount, double maxSalary, double minSalary, double averageSalary, String filePath) {
        this.department = department;
        this.employeeCount = employeeCount;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.averageSalary = averageSalary;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
