package com.jm_springboot.controllers;

import com.jm_springboot.model.Role;
import com.jm_springboot.model.User;
import com.jm_springboot.repositories.UserRepository;
import com.jm_springboot.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("admin")
public class AdminController {
    private final UserService userService;
    private final UserRepository userRepository;

    public AdminController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping

    public String showAllUsers(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("newUser", new User());
        Set<Role> roles = userService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "users";
    }
}