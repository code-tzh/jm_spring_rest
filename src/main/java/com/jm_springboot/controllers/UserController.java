package com.jm_springboot.controllers;

import com.jm_springboot.model.User;
import com.jm_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public String showUser(Principal principal, Model model){
        User username = userService.getUserByName(principal.getName());
        model.addAttribute("user", username);
        return "user";
    }
}