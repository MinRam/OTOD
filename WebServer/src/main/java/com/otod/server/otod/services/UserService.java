package com.otod.server.otod.services;

import com.otod.server.otod.model.Role;
import com.otod.server.otod.model.User;
import com.otod.server.otod.model.UserInfo;
import com.otod.server.otod.respository.RoleRespository;
import com.otod.server.otod.respository.UserInfoRespository;
import com.otod.server.otod.respository.UserRepository;
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

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void save(User user){
        if(user.getPassword() == null || user.getUsername() == null)
            return;
//        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    public UserInfo  getUserInfo(User user){
        return userInfoRespository.findByUser(user);
    }

    public void registerUser(User user, List<String> userRoleNames,UserInfo userInfo) {
        // 注册身份
        List<Role> userRole = new ArrayList<>();

        for(String userRoleName : userRoleNames){
            userRole.add(roleRespository.findByName(userRoleName));
        }

        user.setRoles(userRole);
        userRepository.save(user);

        // 关联信息表
        userInfo.setSex("男");
        userInfo.setUser(user);
        userInfo.setId(user.getUserId());
        userInfoRespository.save(userInfo);
    }
}
