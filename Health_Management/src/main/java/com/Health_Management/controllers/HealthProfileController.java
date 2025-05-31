package com.Health_Management.controllers;

import com.Health_Management.pojo.HealthProfile;
import com.Health_Management.pojo.User;
import com.Health_Management.services.HealthProfileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HealthProfileController {

    @Autowired
    private HealthProfileService healthProfileService;

    @GetMapping("/healthprofile")
    public String viewProfile(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        HealthProfile profile = healthProfileService.getLatestByUserId(userId);
        if (profile == null) {
            profile = new HealthProfile();
            profile.setUser(new User());
        }
        model.addAttribute("profile", profile);
        return "healthprofile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("profile") HealthProfile profile, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (profile.getUser() == null) {
            profile.setUser(new User());
        }
        profile.getUser().setUserId(userId);

        if (profile.getHeight() != null && profile.getHeight() > 0 && profile.getWeight() != null) {
            double h = profile.getHeight() / 100.0;
            double bmi = profile.getWeight() / (h * h);
            profile.setBmi(Math.round(bmi * 100.0) / 100.0);
        }

        healthProfileService.saveOrUpdate(profile);
        return "redirect:/healthprofile";
    }

    
    @GetMapping(value = "/api/healthprofiles", produces = "application/json")
    @ResponseBody
    public List<HealthProfile> getAllProfilesAPI() {
        return healthProfileService.getAllProfiles();
    }
}
