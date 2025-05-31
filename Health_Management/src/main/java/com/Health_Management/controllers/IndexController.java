package com.Health_Management.controllers;

import com.Health_Management.pojo.User;
import com.Health_Management.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index"; 
    }

    //  Trang đăng nhập
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    //  Xử lý đăng nhập
    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("user") User user, HttpSession session, Model model) {
        User u = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (u != null) {
            session.setAttribute("currentUser", u);
            session.setAttribute("userId", u.getUserId());
            return "redirect:/home";
        }

        model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
        return "login";
    }

    // Trang đăng ký
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Xử lý đăng ký
    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("user") User user, Model model) {
        if (userService.usernameExists(user.getUsername())) {
            model.addAttribute("error", "Tên người dùng đã tồn tại!");
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/login";
    }

   
    @GetMapping("/home")
    public String homeRedirect(HttpSession session) {
        Object currentUser = session.getAttribute("currentUser");
        if (currentUser instanceof User user) {
            if ("expert".equalsIgnoreCase(user.getRole())) {
                return "redirect:/users";
            } else {
                return "redirect:/healthprofile";
            }
        }
        return "redirect:/login";
    }

   
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
