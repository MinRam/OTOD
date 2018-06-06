package com.otod.server.otod.controller;

import com.otod.server.otod.model.User;
import com.otod.server.otod.model.UserInfo;
import com.otod.server.otod.pojos.UserRegisteration;
import com.otod.server.otod.pojos.UserSimpleInfo;
import com.otod.server.otod.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class UserController  {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    private List<User> testMappring(){
        return userService.getAllUsers();
    }

    @Qualifier("getTokenStore")
    @Autowired
    private TokenStore tokenStore;

    @GetMapping("/logouts")
    private void logout(@RequestParam (value = "access_token") String accessToken){
        tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
    }

    @PostMapping("/register")
    private String userRegister(@RequestBody UserRegisteration userRegisteration) {
        if (userService.getUser(userRegisteration.getUsername()) != null)
            return "用户名已存在";

        UserInfo userInfo = new UserInfo();
        userInfo.setTelphone(userRegisteration.getTelephone());
        userInfo.setNickname(userRegisteration.getUsername());

        userService.registerUser(new User(
                "username",
                userRegisteration.getUsername(),
                userRegisteration.getPassword()),
                Arrays.asList("USER"),
                userInfo
        );

        return "success";
    }

    @GetMapping("/getSimpleInfo")
    private UserSimpleInfo getUserSimpleInfo(){

        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());

        UserInfo userInfo = userService.getUserInfo(user);

        UserSimpleInfo simpleInfo = new UserSimpleInfo(userInfo.getNickname(),userInfo.getTelphone(),userInfo.getHeadImage());

        return simpleInfo;
    }

}
