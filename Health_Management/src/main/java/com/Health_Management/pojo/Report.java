/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Health_Management.pojo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private int reportId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "report_date")
    private java.sql.Date reportDate;

    @Column(name = "bmi")
    private float bmi;

    @Column(name = "steps")
    private int steps;

    @Column(name = "calories_burned")
    private float caloriesBurned;

    @Column(name = "feedback")
    private String feedback;

    // Getters & Setters...

    /**
     * @return the reportId
     */
    public int getReportId() {
        return reportId;
    }

    /**
     * @param reportId the reportId to set
     */
    public void setReportId(int reportId) {
        this.reportId = reportId;
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
     * @return the reportDate
     */
    public java.sql.Date getReportDate() {
        return reportDate;
    }

    /**
     * @param reportDate the reportDate to set
     */
    public void setReportDate(java.sql.Date reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * @return the bmi
     */
    public float getBmi() {
        return bmi;
    }

    /**
     * @param bmi the bmi to set
     */
    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    /**
     * @return the steps
     */
    public int getSteps() {
        return steps;
    }

    /**
     * @param steps the steps to set
     */
    public void setSteps(int steps) {
        this.steps = steps;
    }

    /**
     * @return the caloriesBurned
     */
    public float getCaloriesBurned() {
        return caloriesBurned;
    }

    /**
     * @param caloriesBurned the caloriesBurned to set
     */
    public void setCaloriesBurned(float caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    /**
     * @return the feedback
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * @param feedback the feedback to set
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
