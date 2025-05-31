package com.Health_Management.controllers;

import com.Health_Management.pojo.Plan;
import com.Health_Management.pojo.User;
import com.Health_Management.pojo.Workout;
import com.Health_Management.services.PlanService;
import com.Health_Management.services.WorkoutService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private PlanService planService;

 
    @GetMapping("/workout")
    public String listWorkouts(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null)
            return "redirect:/login";

        Date today = new Date(System.currentTimeMillis());
        List<Workout> workouts = workoutService.getWorkoutsByUser(currentUser.getUserId());
        List<Plan> todayPlans = planService.getPlansByUserAndDate(currentUser.getUserId(), today);

        model.addAttribute("workouts", workouts);
        model.addAttribute("todayPlans", todayPlans);
        model.addAttribute("workout", new Workout());

        return "workout";
    }

    @PostMapping("/workout/save")
    public String saveWorkout(@ModelAttribute("workout") Workout workout, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null)
            return "redirect:/login";

        workout.setUser(currentUser);

        if (workout.getCaloriesBurned() == null)
            workout.setCaloriesBurned(0);

        if (workout.getDuration() == null)
            workout.setDuration(0);

        if (workout.getWorkoutDate() == null)
            workout.setWorkoutDate(new Date(System.currentTimeMillis()));

        workoutService.saveOrUpdateWorkout(workout);
        return "redirect:/workout";
    }

    // === API CHO REACT 
    @GetMapping(value = "/api/workout", produces = "application/json")
    @ResponseBody
    public List<Workout> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    @GetMapping(value = "/api/workout/user/{userId}", produces = "application/json")
    @ResponseBody
    public List<Workout> getWorkoutsByUser(@PathVariable int userId) {
        return workoutService.getWorkoutsByUser(userId);
    }

    @PostMapping("/api/workout")
    @ResponseBody
    public void createWorkout(@RequestBody Workout workout) {
        workoutService.saveOrUpdateWorkout(workout);
    }

    @PutMapping("/api/workout/{id}")
    @ResponseBody
    public void updateWorkout(@PathVariable int id, @RequestBody Workout workout) {
        workout.setWorkoutId(id);
        workoutService.saveOrUpdateWorkout(workout);
    }

    
}
