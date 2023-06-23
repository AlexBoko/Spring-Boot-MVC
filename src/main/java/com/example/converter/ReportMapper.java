package com.example.converter;

import com.example.application.report.model.Report;
import com.example.application.report.model.ReportDto;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public ReportDto toReportDto(Report report) {
        ReportDto reportDto = new ReportDto();
        reportDto.setDepartment(report.getDepartment());
        reportDto.setEmployeeCount(report.getEmployeeCount());
        reportDto.setMaxSalary(report.getMaxSalary());
        reportDto.setMinSalary(report.getMinSalary());
        reportDto.setAverageSalary(report.getAverageSalary());
        reportDto.setFilePath(report.getFilePath());
        return reportDto;
    }

    public Report toReport(ReportDto reportDto) {
        Report report = new Report();
        report.setDepartment(reportDto.getDepartment());
        report.setEmployeeCount(reportDto.getEmployeeCount());
        report.setMaxSalary(reportDto.getMaxSalary());
        report.setMinSalary(reportDto.getMinSalary());
        report.setAverageSalary(reportDto.getAverageSalary());
        report.setFilePath(reportDto.getFilePath());
        return report;
    }
}
