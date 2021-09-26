package com.example.reg_login.service.impl;

import com.example.reg_login.db.Database;
import com.example.reg_login.model.User;
import com.example.reg_login.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getAllUsers() {
        return Database.getDatabase().getUsers();
    }

    @Override
    public User getUserById(int id) {
        for (User user:Database.getDatabase().getUsers()) {
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        Database.getDatabase().getUsers().add(user);
    }

    @Override
    public void updateUser(int id, User user) {
        List<User> users = Database.getDatabase().getUsers();
        for(int i = 0; i < users.size(); i++){
            User u = users.get(i);
            if (u.getId() == id){
                users.set(i, user);
            }
        }
    }
}
