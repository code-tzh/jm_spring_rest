package com.jm_springboot.controllers;

import com.jm_springboot.model.User;
import com.jm_springboot.repositories.UserRepository;
import com.jm_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public RestController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value ="/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id) {
        User user = userService.showUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value ="/infoUser")
    @ResponseBody
    public User infoUser(@AuthenticationPrincipal User user_authentication){
        User user = userRepository.getUserByEmail(user_authentication.getEmail());
        return user;
    }

    @GetMapping(value = "/findUser/{id}")
    public User findUser(@PathVariable Long id) {
        return userService.showUser(id);
    }

    @PutMapping(value ="/updateUser")
    public ResponseEntity<?> editUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/addUser")
    private ResponseEntity<User> addUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    private void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
