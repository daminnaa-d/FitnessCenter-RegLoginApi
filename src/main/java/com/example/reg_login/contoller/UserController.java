package com.example.reg_login.contoller;

import com.example.reg_login.db.Database;
import com.example.reg_login.model.User;
import com.example.reg_login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllBooks(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("")
    public void addUser (@RequestBody User user){
        userService.addUser(user);
    }

    @PostMapping("/update/{id}")
    public void updateUser(@RequestBody User user, @PathVariable int id) {
        userService.updateUser(id, user);
    }

    @PostMapping("/login")
    public boolean loginUser(@RequestBody User u){
        for( User user: Database.getDatabase().getUsers()){
            if(user.getName().equals(u.getName()) && user.getPswd().equals(u.getPswd())){
                return true;
            }
        }
        return false;
    }



}
