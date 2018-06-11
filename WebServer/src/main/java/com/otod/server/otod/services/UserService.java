package com.otod.server.otod.services;

import com.otod.server.otod.model.*;
import com.otod.server.otod.respository.*;
import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRespository userInfoRespository;

    @Autowired
    private RoleRespository roleRespository;

    @Autowired
    private UserFollowRespository userFollowRespository;

    @Autowired
    private NoticeRespository noticeRespository;

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        String idForEncode = "bcrypt";
//
//        Map encoders = new HashMap<>();
//        encoders.put(idForEncode, new BCryptPasswordEncoder());
//        encoders.put("noop", NoOpPasswordEncoder.getInstance());
//        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
//        encoders.put("scrypt", new SCryptPasswordEncoder());
//        encoders.put("sha256", new StandardPasswordEncoder());
//
//        return new DelegatingPasswordEncoder(idForEncode,encoders);
//    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    // 获取 User

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // 获取 UserInfo
    public UserInfo  getUserInfo(User user){
        return userInfoRespository.findByUser(user);
    }

    public UserInfo getUserInfo(String nickname) {
        return userInfoRespository.findByNickname(nickname);
    }

    public List<Notice> getAllNotices(User user){
        return noticeRespository.findAllByUserOwn(user);
    }

    // 注册新用户
    public void registerUser(User user, List<String> userRoleNames,UserInfo userInfo) {
        // 注册身份
        List<Role> userRole = new ArrayList<>();

        for(String userRoleName : userRoleNames){
            userRole.add(roleRespository.findByName(userRoleName));
        }

        if(user.getPassword() == null || user.getUsername() == null)
            return;
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setRoles(userRole);
        userRepository.save(user);

        // 关联信息表
        userInfo.setSex("男");
        userInfo.setUser(user);
        userInfoRespository.save(userInfo);
    }

    // 获取自己关注的用户列表
    public List<User> getUserFollow(User user){
        List<UserFollow> userFollowsList =  userFollowRespository.findAllByUser(user);
        List<User> userList = new ArrayList<>();

        for(UserFollow userFollow : userFollowsList){
            User userTemp = userFollow.getUserFollow();
            userList.add(userTemp);
        }
        return userList;
    }

    // 获取自己被关注的用户列表
    public List<User> getUserFollowed(User user){
        List<UserFollow> userFollowsList = userFollowRespository.findAllByUserFollow(user);
        List<User> userList = new ArrayList<>();

        for(UserFollow userFollow : userFollowsList) {
            User userTemp = userFollow.getUser();
            userList.add(userTemp);
        }
        return userList;
    }

    // 关注用户
    public boolean followUser(User user,String nickname){
        User followUser = this.getUserInfo(nickname).getUser();
        if(followUser == null) return false;
        else if(userFollowRespository.findAllByUserAndUserFollow(user,followUser)!= null){
            this.userFollowRespository.save(new UserFollow(user,followUser));
            return true;
        }else return false;
    }
}