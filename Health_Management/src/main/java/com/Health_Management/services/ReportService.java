/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Health_Management.services;

import com.Health_Management.pojo.Report;
import com.Health_Management.Repository.ReportRepository;
import java.util.List;

public class ReportService {
    private final ReportRepository reportRepository = new ReportRepository();

    public boolean addReport(Report report) {
        return reportRepository.addReport(report);
    }

    public List<Report> getByUserId(int userId) {
        return reportRepository.getReportsByUserId(userId);
    }

    public boolean deleteReport(int id) {
        return reportRepository.deleteReport(id);
    }
}
