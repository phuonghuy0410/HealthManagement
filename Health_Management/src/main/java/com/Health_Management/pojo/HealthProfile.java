/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Health_Management.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "healthprofiles")
public class HealthProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private int profileId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "bmi")
    private float bmi;

    @Column(name = "water_intake")
    private float waterIntake;

    @Column(name = "steps")
    private int steps;

    @Column(name = "heart_rate")
    private int heartRate;

    // Getters & Setters...

    /**
     * @return the profileId
     */
    public int getProfileId() {
        return profileId;
    }

    /**
     * @param profileId the profileId to set
     */
    public void setProfileId(int profileId) {
        this.profileId = profileId;
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
     * @return the waterIntake
     */
    public float getWaterIntake() {
        return waterIntake;
    }

    /**
     * @param waterIntake the waterIntake to set
     */
    public void setWaterIntake(float waterIntake) {
        this.waterIntake = waterIntake;
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
     * @return the heartRate
     */
    public int getHeartRate() {
        return heartRate;
    }

    /**
     * @param heartRate the heartRate to set
     */
    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }
}
