package com.Health_Management.controllers;

import com.Health_Management.services.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService = new ReportService();

    @GetMapping
    public String reportPage(Model model) {
        model.addAttribute("summary", "Dữ liệu thống kê tổng hợp...");
        return "report";
    }
}
