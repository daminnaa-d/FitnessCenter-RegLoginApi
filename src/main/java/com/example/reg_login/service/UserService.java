package com.example.reg_login.service;

import com.example.reg_login.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    void addUser (User user);
    void updateUser(int id, User user);

}
