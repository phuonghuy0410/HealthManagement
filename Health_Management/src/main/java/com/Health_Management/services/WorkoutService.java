/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Health_Management.services;

import com.Health_Management.pojo.Workout;
import com.Health_Management.Repository.WorkoutRepository;
import java.util.List;

public class WorkoutService {
    private final WorkoutRepository workoutRepository = new WorkoutRepository();

    public boolean addWorkout(Workout workout) {
        return workoutRepository.addWorkout(workout);
    }

    public List<Workout> getByUserId(int userId) {
        return workoutRepository.getWorkoutsByUserId(userId);
    }

    public boolean deleteWorkout(int id) {
        return workoutRepository.deleteWorkout(id);
    }
}
