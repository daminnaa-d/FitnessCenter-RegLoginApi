package com.example.reg_login.service.impl;

import com.example.reg_login.model.User;
import com.example.reg_login.repository.UserRepository;
import com.example.reg_login.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @HystrixCommand(
            fallbackMethod = "getAllUsersFallback",
            threadPoolKey = "getAllUsers",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllUsersFallback() {
        return null;
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "getUserByIdFallback",
            threadPoolKey = "getUserById",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
    public Optional<User> getUserById(int id) {
        return userRepository.findAll().stream().filter(user -> user.getId() == id).findFirst();
    }

    public User getUserByIdFallback(int id) {
        User user = new User();
        user.setId(0);
        user.setName("Name is not available: Service unavailable!");
        user.setPhoneNumber("Phone number is not available: Service unavailable!");
        user.setBirthday("Birth date is not available: Service unavailable!");
        user.setMoney(0);
        user.setPswd("Password is not available: Service unavailable!");
        return user;
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "addUserFallback",
            threadPoolKey = "addUser",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
    public void addUser(User user) {
       userRepository.save(user);
    }

    public void addUserFallback(User user) {
        System.out.println("Cannot add user: Service unavailable!");
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "updateUserFallback",
            threadPoolKey = "updateUser",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
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

    public void updateUserFallback(int id, User user) {
        System.out.println("Cannot update user: Service unavailable!");
    }

}
