/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Health_Management.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Az
 */
@Controller
public class IndexControllers {
  
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("msg", "xin chào");
return "index";
}
       
}
