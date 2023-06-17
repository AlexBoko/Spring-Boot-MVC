package com.example.application.report.controller;

import com.example.application.report.model.ReportDto;
import com.example.application.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<Long> generateReport(@RequestParam("department") String department) {
        Long reportId = reportService.generateReport(department);
        return ResponseEntity.ok(reportId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getReportById(@PathVariable Long id) {
        ReportDto reportDto = reportService.getReportById(id);
        if (reportDto != null) {
            return ResponseEntity.ok(reportDto);
        }
        return ResponseEntity.notFound().build();
    }
}
