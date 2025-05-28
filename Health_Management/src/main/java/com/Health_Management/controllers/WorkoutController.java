package com.Health_Management.controllers;

import com.Health_Management.pojo.Workout;
import com.Health_Management.services.WorkoutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/workout")
public class WorkoutController {
    private final WorkoutService workoutService = new WorkoutService();

    @GetMapping
public String workoutPage(Model model) {
    model.addAttribute("workouts", workoutService.getByUserId(1));
    return "workout"; // templates/workout.html
}

}
