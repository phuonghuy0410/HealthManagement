/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Health_Management.services;

import com.Health_Management.pojo.Reminder;
import com.Health_Management.Repository.ReminderRepository;
import java.util.List;

public class ReminderService {
    private final ReminderRepository reminderRepository = new ReminderRepository();

    public boolean addReminder(Reminder reminder) {
        return reminderRepository.addReminder(reminder);
    }

    public List<Reminder> getByUserId(int userId) {
        return reminderRepository.getRemindersByUser(userId);
    }

    public boolean deleteReminder(int id) {
        return reminderRepository.deleteReminder(id);
    }
}
