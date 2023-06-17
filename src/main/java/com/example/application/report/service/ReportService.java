package com.example.application.report.service;

import com.example.application.report.model.Employee;
import com.example.application.report.model.Report;
import com.example.application.report.repository.ReportRepository;
import com.example.application.report.model.ReportDto;
import com.example.converter.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    private final String reportDirectory = "C:/reports/";

    @Autowired
    public ReportService(ReportRepository reportRepository, ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }

    public Long generateReport(String department) {
        List<Employee> employees = null; // Получение списка сотрудников из базы данных или другого источника

        int employeeCount = employees.size();
        double maxSalary = employees.stream().mapToDouble(Employee::getSalary).max().orElse(0);
        double minSalary = employees.stream().mapToDouble(Employee::getSalary).min().orElse(0);
        double averageSalary = employees.stream().mapToDouble(Employee::getSalary).average().orElse(0);

        Report report = new Report(department, employeeCount, maxSalary, minSalary, averageSalary, null);
        Report savedReport = reportRepository.save(report);

        String json = generateJsonReport(savedReport);
        String filePath = saveJsonReport(json, savedReport.getId());

        savedReport.setFilePath(filePath);
        reportRepository.save(savedReport);

        return savedReport.getId();
    }

    private String generateJsonReport(Report report) {
        ReportDto reportDto = reportMapper.toReportDto(report);
        String json = convertReportDtoToJson(reportDto);

        return json;
    }

    private String convertReportDtoToJson(ReportDto reportDto) {
        // Код преобразования reportDto в JSON-строку
        // ...

        return reportDirectory;
    }

    private String saveJsonReport(String json, Long reportId) {
        String fileName = "report_" + reportId + ".json";
        String filePath = reportDirectory + fileName;

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }

    public ReportDto getReportById(Long id) {
        return reportRepository.findById(id).map(reportMapper::toReportDto).orElse(null);
    }
}
