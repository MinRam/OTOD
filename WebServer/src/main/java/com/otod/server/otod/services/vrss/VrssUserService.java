package com.otod.server.otod.services.vrss;


import com.otod.server.otod.model.user.User;
import com.otod.server.otod.model.user.UserInfo;
import com.otod.server.otod.model.vrss.VrssUser;
import com.otod.server.otod.respository.UserInfoRespository;
import com.otod.server.otod.respository.vrss.VrssUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VrssUserService {
    @Autowired
    private VrssUserRepository vrssUserRepository;
    @Autowired
    private UserInfoRespository userInfoRespository;
    public String getName(Integer user_id){
        return vrssUserRepository.findById(user_id).get().getUser_name();
    }
    public void register(User user){
        UserInfo userInfo=userInfoRespository.findById(user.getUserId()).get();
        VrssUser vrssUser=new VrssUser();
        vrssUser.setUser_id(user.getUserId().intValue());
        vrssUser.setUser_name(userInfo.getNickname());
        vrssUserRepository.save(vrssUser);
    }
}
