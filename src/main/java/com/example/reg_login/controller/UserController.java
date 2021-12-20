package com.example.reg_login.controller;

import com.example.reg_login.model.User;
import com.example.reg_login.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "User Controller class", description = "This class allows to interact with User object")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    @ApiOperation(value = "Method to get list of users", response = List.class)
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Method to get user by ID", response = List.class)
    public ResponseEntity<?> getUserById(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("")
    @ApiOperation(value = "Method to add a new user", response = List.class)
    public void addUser (@RequestBody User user){
        userService.addUser(user);
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "Method to update user", response = List.class)
    public void updateUser(@RequestBody User user, @PathVariable int id) {
        userService.updateUser(id, user);
    }

    @PostMapping("/login")
    @ApiOperation(value = "Method to login", response = List.class)
    public boolean loginUser(@RequestBody User u){
        for( User user: userService.getAllUsers()){
            if(user.getName().equals(u.getName()) && user.getPswd().equals(u.getPswd())){
                return true;
            }
        }
        return false;
    }
}
