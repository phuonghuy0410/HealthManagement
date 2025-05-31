package com.Health_Management.controllers;

import com.Health_Management.pojo.User;
import com.Health_Management.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")  // Cho phép React truy cập
public class UserApiController {

    @Autowired
    private UserService userService;

    // ? API: Đăng nhập từ React
    @PostMapping("/login")
    public User login(@RequestBody User loginUser, HttpSession session) {
        System.out.println("Login API called with: " + loginUser.getUsername());

        User user = userService.getUserByUsernameAndPassword(
            loginUser.getUsername(),
            loginUser.getPassword()
        );

        if (user != null) {
            session.setAttribute("currentUser", user);
            return user;
        }

        return null; // frontend sẽ kiểm tra nếu là null là sai tài khoản
    }

    // 📝 API: Đăng ký tài khoản từ React
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        System.out.println("Register API called with: " + user.getUsername());

        if (userService.usernameExists(user.getUsername()))
            return "EXISTS";

        userService.saveUser(user);
        return "SUCCESS";
    }

    // 🔍 API: Lấy user theo ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    // 📄 API: Lấy danh sách tất cả user
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
