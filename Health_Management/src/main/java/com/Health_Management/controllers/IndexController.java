package com.Health_Management.controllers;

import com.Health_Management.pojo.User;
import com.Health_Management.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    private final UserService userService = new UserService();

    @GetMapping
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }
}
