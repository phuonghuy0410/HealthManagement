/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Health_Management.pojo;

import jakarta.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "reminders")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reminder_id")
    private int reminderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "reminder_type")
    private String reminderType;

    @Column(name = "message")
    private String message;

    @Column(name = "reminder_time")
    private java.sql.Time reminderTime;

 

    /**
     * @return the reminderId
     */
    public int getReminderId() {
        return reminderId;
    }

    /**
     * @param reminderId the reminderId to set
     */
    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the reminderType
     */
    public String getReminderType() {
        return reminderType;
    }

    /**
     * @param reminderType the reminderType to set
     */
    public void setReminderType(String reminderType) {
        this.reminderType = reminderType;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the reminderTime
     */
    public java.sql.Time getReminderTime() {
        return reminderTime;
    }

    /**
     * @param reminderTime the reminderTime to set
     */
    public void setReminderTime(java.sql.Time reminderTime) {
        this.reminderTime = reminderTime;
    }
}
