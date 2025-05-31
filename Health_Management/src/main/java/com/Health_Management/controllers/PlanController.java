package com.Health_Management.controllers;

import com.Health_Management.pojo.Plan;
import com.Health_Management.pojo.User;
import com.Health_Management.services.PlanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlanController {

    @Autowired
    private PlanService planService;

    
    @GetMapping("/plan")
    public String listPlans(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null)
            return "redirect:/login";

        model.addAttribute("plans", planService.getPlansByUser(currentUser.getUserId()));
        model.addAttribute("plan", new Plan());
        return "plan";
    }

    @PostMapping("/plan/save")
    public String savePlan(@ModelAttribute("plan") Plan plan, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null)
            return "redirect:/login";

        plan.setUser(currentUser);
        planService.savePlan(plan);
        return "redirect:/plan";
    }

    // Các API JSON cho ReactJS
    @GetMapping("/api/plan")
    @ResponseBody
    public List<Plan> getAllPlansAPI() {
        return planService.getAllPlans();
    }

    @GetMapping("/api/plan/user/{userId}")
    @ResponseBody
    public List<Plan> getPlansByUser(@PathVariable int userId) {
        return planService.getPlansByUser(userId);
    }

    @PostMapping("/api/plan")
    @ResponseBody
    public void createPlan(@RequestBody Plan plan) {
        planService.savePlan(plan);
    }
}
