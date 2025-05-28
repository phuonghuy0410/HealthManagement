/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Health_Management.pojo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private int workoutId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "workout_date")
    private java.sql.Date workoutDate;

    @Column(name = "exercise_name")
    private String exerciseName;

    @Column(name = "duration")
    private int duration;

    @Column(name = "calories_burned")
    private float caloriesBurned;

    // Getters & Setters...

    /**
     * @return the workoutId
     */
    public int getWorkoutId() {
        return workoutId;
    }

    /**
     * @param workoutId the workoutId to set
     */
    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
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
     * @return the workoutDate
     */
    public java.sql.Date getWorkoutDate() {
        return workoutDate;
    }

    /**
     * @param workoutDate the workoutDate to set
     */
    public void setWorkoutDate(java.sql.Date workoutDate) {
        this.workoutDate = workoutDate;
    }

    /**
     * @return the exerciseName
     */
    public String getExerciseName() {
        return exerciseName;
    }

    /**
     * @param exerciseName the exerciseName to set
     */
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
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
}
