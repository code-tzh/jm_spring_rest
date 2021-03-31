package com.jm_springboot.service;

import com.jm_springboot.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    void addUser(User user);
    void deleteUser(Long id);
    User editUser(User user);
    User getById(Long id);
    User getUserByName(String username);
}