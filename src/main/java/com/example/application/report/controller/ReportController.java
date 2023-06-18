package com.example.application.report.controller;

import com.example.application.report.model.ReportDto;
import com.example.application.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/generate")
    public ResponseEntity<Long> generateReport(@RequestParam("department") String department) {
        Long reportId = reportService.generateReport(department);
        return ResponseEntity.ok(reportId);
    }

    @PostMapping("/employees/upload")
    public ResponseEntity<String> uploadEmployeesFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String filePath = reportService.saveEmployeeFile(file);
                if (filePath != null) {
                    return ResponseEntity.ok("File uploaded successfully");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to upload file");
    }


    @GetMapping("/report/{id}")
    public ResponseEntity<File> downloadReportFile(@PathVariable Long id) {
        ResponseEntity<File> reportFileResponse = reportService.getReportFile(id);
        if (reportFileResponse != null) {
            File reportFile = reportFileResponse.getBody();
            if (reportFile != null) {
                byte[] fileBytes = null;

                try {
                    fileBytes = Files.readAllBytes(reportFile.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (fileBytes != null) {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report_" + id + ".json");
                    return ResponseEntity.ok().headers(headers).body(reportFile);
                }
            }
        }

        return ResponseEntity.notFound().build();
    }
}
