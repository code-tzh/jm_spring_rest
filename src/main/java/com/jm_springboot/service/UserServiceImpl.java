package com.jm_springboot.service;

import com.jm_springboot.repositories.RoleRepository;
import com.jm_springboot.model.Role;
import com.jm_springboot.model.User;
import com.jm_springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@EnableJpaRepositories(basePackages ={"com.jm_springboot.repositories"})
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User showUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.getUserByEmail(username);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user, Long id) {
        User updateUser= userRepository.findById(id).orElse(null);
        userRepository.saveAndFlush(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Role getRoleByName(String role) {
        return roleRepository.findByRole(role);
    }
}
