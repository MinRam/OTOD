package com.otod.server.otod.controller;

import com.otod.server.otod.model.user.*;
import com.otod.server.otod.pojos.*;
import com.otod.server.otod.services.NoticeService;
import com.otod.server.otod.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    @Value("${cbs.imagesPath}")
    private String webUploadPath;

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

    @GetMapping("/user/getSimpleByUsername")
    private UserSimpleInfo getSimpleByUsername(@RequestParam (value = "username") String username){
        User user = userService.getUser(username);
        if (user == null)
            return new UserSimpleInfo();
        else
            return new UserSimpleInfo(userService.getUserInfo(user));
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

    @GetMapping("/user/getUserNum")
    private UserNums getUserNums(){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        UserInfo userInfo = userService.getUserInfo(user);

        UserNums userNums = new UserNums();
        userNums.setDynamics(userService.getUpdateNumBySender(userInfo).intValue());
        userNums.setFavors(userService.getUpdateFavorNumByUser(user).intValue());
        userNums.setFollow(userService.getUserFollowNumByUser(user).intValue());
        userNums.setFollowed(userService.getUserFollowedNumByUser(user).intValue());
        userNums.setNotices(noticeService.getNewNoticesNum(user).intValue());

        return userNums;
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
        return userService.followUser(user,nickname)?"success":"failure";
    }

    // INFO

    @GetMapping("/user/getAllInfo")
    private UserInfo allInfo(){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        return userService.getUserInfo(user);
    }

    @PostMapping("/user/setAllInfo")
    private String setInfo(@RequestBody  UserInfo userInfo){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        UserInfo oldInfo = userService.getUserInfo(user);

        oldInfo.setNickname(userInfo.getNickname());
        oldInfo.setSex(userInfo.getSex());
        oldInfo.setAddress(userInfo.getAddress());
        oldInfo.setEmail(userInfo.getEmail());
        oldInfo.setPosition(userInfo.getPosition());
        oldInfo.setHeadImage(userInfo.getHeadImage());
        oldInfo.setAge(userInfo.getAge());

        userService.save(oldInfo);
        return "success";
    }

    @PostMapping("/user/saveImg")
    public String saveimg(@RequestParam("file") MultipartFile file) throws IOException{
        if (!file.isEmpty()) {
            if (file.getContentType().contains("image")) {
                {
                    // 头像地址
                    String srcUrl = "hp" + File.separator;
                    // 获取图片的扩展名
                    String extensionName = StringUtils.substringAfter(file.getOriginalFilename(), ".");
                    // 新的图片文件名 = 获取时间戳+"."图片扩展名
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
                    // 数据库保存的目录
                    String dataDirectory = srcUrl;
                    // 文件路径
                    String filePath = webUploadPath.concat(dataDirectory);
                    File dest = new File(filePath, newFileName);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    System.out.println("[service]:Upload-HeadImage:"+dest.getPath());
                    file.transferTo(dest);
                    // 将反斜杠转换为正斜杠
                    return dataDirectory.replaceAll("\\\\", "/") + newFileName;
                }
            } else {
                return "Error!";
            }
        } else {
            return "Empty!";
        }
    }

    // Notice
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
        noticeService.readNotices(noticeList.getIdList());
    }

    // UpdateList
    @GetMapping("/user/getUpdateList")
    private List<UserUpdate> getUpdteList(@RequestParam(value = "page") Integer page){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        UserInfo userInfo =  userService.getUserInfo(user);

        List<User> userfollow = userService.getUserFollow(user);
        Page<Update> list = userService.getUpdateByAllUser(userfollow,page);

        List<Update> updateList = list.getContent();
        List<UserUpdate> userUpdates = new ArrayList<>();

        for(Update update: updateList){
            UserUpdate userUpdate = new UserUpdate(update);
            userUpdate.setAuthor(new UserSimpleInfo(update.getUserSender()));
            userUpdate.setUpdateTags(update.getTags());
            userUpdate.setUpdateOption(new UpdateOption(userService.getUpdateOption(user,update)!= null));
            userUpdates.add(userUpdate);
        }

        return userUpdates;
    }

    @GetMapping("/user/getUserUpdateList")
    private List<UserUpdate> getUserUpdateLIst(@RequestParam(value ="page") Integer page){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());

        Page<Update> list = userService.getUpdateByUser(user,page);

        List<Update> updateList = list.getContent();
        List<UserUpdate> userUpdates = new ArrayList<>();

        for(Update update: updateList){
            UserUpdate userUpdate = new UserUpdate(update);
            userUpdate.setAuthor(new UserSimpleInfo(update.getUserSender()));
            userUpdate.setUpdateTags(update.getTags());
            userUpdate.setUpdateOption(new UpdateOption(userService.getUpdateOption(user,update)!= null));
            userUpdates.add(userUpdate);
        }

        return userUpdates;
    }

    @GetMapping("/user/favorUpdate")
    private String favorUpdate(@RequestParam (value="update_id") Long updateId){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());

        userService.favorUpdate(user,updateId);

        return "success";
    }

    @GetMapping("/user/noFavorUpdate")
    private String noFavorUpdate(@RequestParam (value="update_id") Long updateId) {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());

        userService.noFavorUpdate(user,updateId);

        return "success";
    }

    @GetMapping("/user/updateTags")
    private List<Map<String,String>> getUpdateTags(){

        List<Map<String,String>> result = new ArrayList<>();
        List<UpdateTag> updateTags = userService.getAllUpdateTags();

        for(UpdateTag updateTag : updateTags) {
            Map<String,String> tags = new HashMap<>();
            tags.put("Id",updateTag.getUpdateTagId().toString());
            tags.put("Name",updateTag.getTagName());
            result.add(tags);
        }
        return result;
    }
}
