package com.jm_springboot.service;

import com.jm_springboot.model.Role;
import com.jm_springboot.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
public interface UserService {
    public List<User> allUsers();
    public User showUser(Long id);
    public User getUserByName(String username);
    public void addUser(User user);
    public void updateUser(User user, Long id);
    public void deleteUser(Long id);
    public Set<Role> getAllRoles();
    public Role getRoleByName(String role);
}