package com.jm_springboot.dao;

import com.jm_springboot.model.Role;
import com.jm_springboot.model.User;

import java.util.List;

public interface UserDao {
    public List<User> allUsers();
    public void addUser(User user);
    public void deleteUser(Long id);
    public User editUser(User user);
    public User getById(Long id);
    User getUserByName(String username);
    List<Role> getRoleList();
    public Role getRole(String role);
}

