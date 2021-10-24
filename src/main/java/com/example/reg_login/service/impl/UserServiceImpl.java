package com.example.reg_login.service.impl;

import com.example.reg_login.model.User;
import com.example.reg_login.repository.UserRepository;
import com.example.reg_login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findAll().stream().filter(user -> user.getId() == id).findFirst();

    }

    @Override
    public void addUser(User user) {
       userRepository.save(user);
    }

    @Override
    public void updateUser(int id, User user) {
        try{
            User userDB = userRepository.findById(id).orElse(null);

            if (userDB != null) {
                userDB.setName(user.getName());
                userDB.setPswd(user.getPswd());
                userDB.setMoney(user.getMoney());
                userDB.setBirthday(user.getBirthday());
                userDB.setPhoneNumber(user.getPhoneNumber());

                userRepository.saveAndFlush(userDB);
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }
}
