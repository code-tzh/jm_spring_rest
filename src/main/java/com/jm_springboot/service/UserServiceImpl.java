package com.jm_springboot.service;

import com.jm_springboot.dao.UserDao;
import com.jm_springboot.model.Role;
import com.jm_springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements com.jm_springboot.service.UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Override
    public void addUser(User user) {
        if (user.getId() == null) {
            userDao.addUser(user);
        } else userDao.editUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public User editUser(User user) {
        return userDao.editUser(user);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public List <Role> getRoleList() {
        return userDao.getRoleList();
    }

    @Override
    public Role getRole(String role) { return userDao.getRole(role); }
}
