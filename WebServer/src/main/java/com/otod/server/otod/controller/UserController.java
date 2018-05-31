package com.otod.server.otod.controller;

import com.otod.server.otod.model.Role;
import com.otod.server.otod.model.User;
import com.otod.server.otod.pojos.UserRegisteration;
import com.otod.server.otod.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController  {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    private List<User> testMappring(){
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    private String userRegister(@RequestBody UserRegisteration userRegisteration) {
        if (userService.getUser(userRegisteration.getUsername())!=null)
            return "User already exits!";

        userService.save(new User(
                userRegisteration.getUsername(),
                userRegisteration.getPassword(),
                Arrays.asList(new Role("USER"))
        ));
        return "User created!";
    }
}
