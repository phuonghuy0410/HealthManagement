package com.Health_Management.controllers;

import com.Health_Management.pojo.Reminder;
import com.Health_Management.pojo.User;
import com.Health_Management.services.ReminderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    // === Giao diện HTML ===
    @GetMapping("/reminder")
    public String viewReminderPage(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null)
            return "redirect:/login";

        List<Reminder> reminders = reminderService.getRemindersByUser(currentUser.getUserId());
        model.addAttribute("reminders", reminders);
        model.addAttribute("reminder", new Reminder());
        return "reminder";
    }

    @PostMapping("/reminder/save")
    public String saveReminder(@ModelAttribute("reminder") Reminder reminder, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null)
            return "redirect:/login";

        reminder.setUser(currentUser);

        // 
        if (reminder.getReminderDate() == null)
            reminder.setReminderDate(new Date(System.currentTimeMillis()));

        reminderService.addOrUpdateReminder(reminder);
        return "redirect:/reminder";
    }



    @GetMapping("/api/reminder")
    @ResponseBody
    public List<Reminder> getAllReminders() {
        return reminderService.getAll();
    }

    @GetMapping("/api/reminder/user/{userId}")
    @ResponseBody
    public List<Reminder> getRemindersByUser(@PathVariable int userId) {
        return reminderService.getRemindersByUser(userId);
    }

    @PostMapping("/api/reminders")
    @ResponseBody
    public void createReminder(@RequestBody Reminder reminder) {
        reminderService.addOrUpdateReminder(reminder);
    }

    @DeleteMapping("/api/reminder/{id}")
    @ResponseBody
    public void deleteReminder(@PathVariable int id) {
        reminderService.deleteReminder(id);
    }
}
