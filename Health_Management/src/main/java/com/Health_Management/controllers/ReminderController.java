package com.Health_Management.controllers;

import com.Health_Management.pojo.Reminder;
import com.Health_Management.services.ReminderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reminder")
public class ReminderController {
    private final ReminderService reminderService = new ReminderService();

    @GetMapping
public String reminderPage(Model model) {
    model.addAttribute("reminders", reminderService.getByUserId(1));
    return "reminder";
}

}
