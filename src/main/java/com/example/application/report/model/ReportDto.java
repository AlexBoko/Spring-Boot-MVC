package com.example.application.report.model;

public class ReportDto {
    private String department;
    private int employeeCount;
    private double maxSalary;
    private double minSalary;
    private double averageSalary;
    private String filePath;

    public ReportDto() {
    }

    public ReportDto(String department, int employeeCount, double maxSalary, double minSalary, double averageSalary, String filePath) {
        this.department = department;
        this.employeeCount = employeeCount;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.averageSalary = averageSalary;
        this.filePath = filePath;
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
