package com.otod.server.otod.controller;

import com.otod.server.otod.model.User;
import com.otod.server.otod.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController  {
    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    private List<User> testMappring(){
        return userService.getAllUsers();
    }

    @PostMapping("/user/registe")
    private void userRegister(@RequestBody User user){
        userService.registeUser(user);
    }

}
