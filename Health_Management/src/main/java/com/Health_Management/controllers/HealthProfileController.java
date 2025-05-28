package com.Health_Management.controllers;

import com.Health_Management.pojo.HealthProfile;
import com.Health_Management.pojo.User;
import com.Health_Management.services.HealthProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/health")
public class HealthProfileController {

    private final HealthProfileService healthProfileService = new HealthProfileService();

    @GetMapping("/view/{userId}")
    public String viewHealthProfile(@PathVariable int userId, Model model) {
        HealthProfile profile = healthProfileService.getProfileByUserId(userId);

     
        if (profile == null) {
            profile = new HealthProfile();
            User user = new User();
            user.setUserId(userId);
            profile.setUser(user);
        }

        model.addAttribute("profile", profile);
        return "healthprofile";
    }

    @PostMapping("/save")
    public String saveHealthProfile(@ModelAttribute("profile") HealthProfile profile) {
        healthProfileService.saveOrUpdateProfile(profile);
        return "redirect:/health/view/" + profile.getUser().getUserId(); 
    }
}
