package com.Health_Management.controllers;

import com.Health_Management.pojo.User;
import com.Health_Management.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users") // dùng cho Thymeleaf
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Giao diện danh sách người dùng (chỉ khi đã đăng nhập)
    @GetMapping("")
    public String list(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null)
            return "redirect:/login";

        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
}
