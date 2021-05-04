package com.jm_springboot.service;

import com.jm_springboot.model.Role;
import com.jm_springboot.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
public interface UserService {
    List<User> allUsers();
    User showUser(Long id);
    User getUserByName(String username);
    void addUser(User user);
    void updateUser(User user, Long id);
    void deleteUser(Long id);
    Set<Role> getAllRoles();
    Role getRoleByName(String role);
}
