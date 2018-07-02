package com.otod.server.otod.controller;

import com.otod.server.otod.model.user.Notice;
import com.otod.server.otod.model.user.NoticeList;
import com.otod.server.otod.model.user.User;
import com.otod.server.otod.model.user.UserInfo;
import com.otod.server.otod.pojos.NoticePojo;
import com.otod.server.otod.pojos.UserFollowList;
import com.otod.server.otod.pojos.UserRegisteration;
import com.otod.server.otod.pojos.UserSimpleInfo;
import com.otod.server.otod.services.NoticeService;
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

    @Autowired
    private NoticeService noticeService;

    @Qualifier("getTokenStore")
    @Autowired
    private TokenStore tokenStore;

    // test
    @GetMapping("/users")
    private List<User> testMappring(){
        return userService.getAllUsers();
    }

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

    @GetMapping("/user/getSimpleInfo")
    private UserSimpleInfo getUserSimpleInfo(){

        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());

        UserInfo userInfo = userService.getUserInfo(user);

        UserSimpleInfo simpleInfo = new UserSimpleInfo(userInfo);

        return simpleInfo;
    }

    @GetMapping("/user/getSimpleByNickname")
    private UserSimpleInfo getSimpleByNickname(@RequestParam (value = "nickname") String nickname){
        UserInfo userInfo = userService.getUserInfo(nickname);
        if(userInfo != null){
            return new UserSimpleInfo(userInfo);
        }else{
            return new UserSimpleInfo();
        }
    }

    @GetMapping("/user/telephone")
    private String getUserTelephone(@RequestParam (value="telephone") String telephone){
        if(userService.getTelephone(telephone)){
            return "exit";
        } else {
            return "noexit";
        }
    }

    @GetMapping("/user/getfollowInfo")
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

    @GetMapping("/user/followUser")
    private String followUser(@RequestParam (value = "nickname") String nickname){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        return userService.followUser(user,nickname)?"true":"false";
    }

    @GetMapping("/user/getAllInfo")
    private UserInfo allInfo(){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        return userService.getUserInfo(user);
    }

    @GetMapping("/user/setAllInfo")
    private String setInfo(){
        return "success";
    }

    @GetMapping("/user/Notice")
    private List<NoticePojo> notice(){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Notice> noticeList =  noticeService.getAllNewNotices(user);
        List<NoticePojo> noticePojosList = new ArrayList<>();

        for(Notice notice : noticeList){
            UserInfo userInfo = userService.getUserInfo(notice.getUserOut());
            noticePojosList.add(new NoticePojo(notice,userInfo));
        }

        return noticePojosList;
    }

    @GetMapping("/user/getAllNotice")
    private List<NoticePojo> getAllNotice(){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Notice> noticeList =  noticeService.getAllNotices(user);
        List<NoticePojo> noticePojosList = new ArrayList<>();

        for(Notice notice : noticeList){
            UserInfo userInfo = userService.getUserInfo(notice.getUserOut());
            noticePojosList.add(new NoticePojo(notice,userInfo));
        }

        return noticePojosList;
    }

    @PostMapping("/user/readNotice")
    private void readNotice(@RequestBody NoticeList noticeList){
        System.out.println("yes");
        noticeService.readNotices(noticeList.getIdList());
    }

    @GetMapping("/user/getUpdateList")
    private List<Integer> getUpdteList(){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        UserInfo userInfo =  userService.getUserInfo(user);



        return null;
    }
}
