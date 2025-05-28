/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Health_Management.services;

import com.Health_Management.pojo.User;
import com.Health_Management.Repository.UserRepository;
import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public boolean deleteUser(int id) {
        return userRepository.deleteUser(id);
    }
}
