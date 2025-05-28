package com.Health_Management.controllers;

import com.Health_Management.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService = new UserService();

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index"; 
    }
}
