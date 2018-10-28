package com.otod.server.otod.services;

import com.otod.server.otod.model.Market_user;
import com.otod.server.otod.model.user.*;
import com.otod.server.otod.respository.*;
import com.otod.server.otod.services.vrss.VrssUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    private MURepository mURepository;

    @Autowired
    private UserUpdateRepository userUpdateRepository;

    @Autowired
    private VrssUserService vrssUserService;

    @Autowired
    private UserFavorRepository userFavorRepository;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserUpdateTagRepository userUpdateTagRepository;
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

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // 获取 UserInfo
    public UserInfo getUserInfo(User user){
        return userInfoRespository.findByUser(user);
    }

    public UserInfo getUserInfo(String nickname) {
        return userInfoRespository.findByNickname(nickname);
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
        userInfo.setHeadImage("hp/default.png");
        userInfoRespository.save(userInfo);

        // 设置mUser
        Market_user mUser = new Market_user();
        mUser.setUserInfo(userInfo);
        mURepository.save(mUser);
        // 设置vrssuser
        vrssUserService.register(userInfo);
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
        System.out.println(followUser.getUserId());
        if(followUser == null) return false;
        else if(userFollowRespository.findAllByUserAndUserFollow(user,followUser)== null){
            this.userFollowRespository.save(new UserFollow(user,followUser));
            return true;
        }else return false;
    }

    // 检索手机号
    public boolean getTelephone(String telephone) {
       return userInfoRespository.findByTelphone(telephone)!= null;
    }

    // 更新用户信息
    public void save(UserInfo userInfo) {
        userInfoRespository.save(userInfo);
    }

    public Page<Update> getUserUpdate(UserInfo userInfo, Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "date");
        return userUpdateRepository.findAll(pageable);
    }

    public List<Update> getUserUpdate(UserInfo userInfo){
        return userUpdateRepository.findAllByUserSender(userInfo);
    }

    // get Nums
    public Long getUpdateNumBySender(UserInfo userInfo){
        return userUpdateRepository.countByUserSender(userInfo);
    }

    public Long getUpdateFavorNumByUser(User user){
        return userFavorRepository.countByUser(user);
    }

    public Long getUserFollowNumByUser(User user){
        return userFollowRespository.countByUser(user);
    }

    public Long getUserFollowedNumByUser(User user){
        return userFollowRespository.countByUserFollow(user);
    }

    public Page<Update> getUpdateByAllUser(List<User> userfollow,Integer page) {
        List<UserInfo> userInfos = new ArrayList<>();
        for(User user:userfollow){
            userInfos.add(this.getUserInfo(user));
        }
        Page<Update> updatePage = userUpdateRepository.findAllByUserSenderIn(userInfos, PageRequest.of(page,6
                ,new Sort(Sort.Direction.DESC,"date")));

        return updatePage;
    }

    public UserFavor getUpdateOption(User user, Update update) {
        return userFavorRepository.findByUserAndUpdate(user,update);
    }

    public void favorUpdate(User user, Long updateId) {
        Update update = userUpdateRepository.findByUpdateId(updateId);
        userFavorRepository.save(new UserFavor(user,update));

        Notice notice = new Notice();
        notice.setAddtime(new Date());
        notice.setObject(updateId.intValue());
        notice.setTitle("喜欢了您的动态：");
        notice.setUserOwn(update.getUserSender().getUser());
        notice.setUserOut(user);
        notice.setRead(1);

        noticeService.addNotice(notice);
    }

    public void noFavorUpdate(User user, Long updateId) {
        Update update = userUpdateRepository.findByUpdateId(updateId);
        userFavorRepository.deleteByUserAndUpdate(user,update);
    }

    public List<UpdateTag> getAllUpdateTags() {
        return userUpdateTagRepository.findAll();
    }

    public Page<Update> getUpdateByUser(User user, Integer page) {
        return userUpdateRepository.findAllByUserSender(this.getUserInfo(user),PageRequest.of(page,6
                ,new Sort(Sort.Direction.DESC,"date")));
    }
}