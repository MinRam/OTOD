package com.otod.server.otod.controller;

import com.otod.server.otod.model.User;
import com.otod.server.otod.model.UserInfo;
import com.otod.server.otod.pojos.UserFollowList;
import com.otod.server.otod.pojos.UserRegisteration;
import com.otod.server.otod.pojos.UserSimpleInfo;
import com.otod.server.otod.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        UserSimpleInfo simpleInfo = new UserSimpleInfo(userInfo);

        return simpleInfo;
    }

    @GetMapping("/getfollowInfo")
    private UserFollowList getFollowInfo(){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());

        List<User> userFollowList = userService.getUserFollow(user);
        List<User> userFollowedList = userService.getUserFollowed(user);

        List<UserSimpleInfo> userFollowInfoList = new ArrayList<>();
        List<UserSimpleInfo> userFollowedInfoList = new ArrayList<>();

        for(User userFollow : userFollowList){
            UserInfo userInfo = userService.getUserInfo(userFollow);
            userFollowInfoList.add(new UserSimpleInfo(userInfo));
        }

        for(User userFollowed : userFollowedList){
            UserInfo userInfo = userService.getUserInfo(userFollowed);
            userFollowedInfoList.add(new UserSimpleInfo(userInfo));
        }
        
        return new UserFollowList(userFollowInfoList,userFollowedInfoList);
    }

    @GetMapping("/getAllInfo")
    private UserInfo allInfo(){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        return userService.getUserInfo(user);
    }
}
