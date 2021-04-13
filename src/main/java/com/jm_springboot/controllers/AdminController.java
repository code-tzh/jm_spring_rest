package com.jm_springboot.controllers;

import com.jm_springboot.model.Role;
import com.jm_springboot.model.User;
import com.jm_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showAllUsers(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("newUser", new User());
        List<Role> roles = userService.getRoleList();
        model.addAttribute("allRoles", roles);
        return "users";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
            model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String add(@ModelAttribute("user") User user,
                      @RequestParam(value = "newRole", required = false) String[] role) {
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/update")
    public String editUser(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", userService.getRoleList());
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(name = "allRoles", required = false) String[] roles){
        userService.editUser(user);
        return "redirect:/admin/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam (value = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }

    @GetMapping("/{id}")
    public String showUser(Principal principal, Model model){
        User username = userService.getUserByName(principal.getName());
        model.addAttribute("user", username);
        return "user";
    }
}