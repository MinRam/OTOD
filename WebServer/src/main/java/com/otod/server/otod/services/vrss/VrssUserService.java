package com.otod.server.otod.services.vrss;

import com.otod.server.otod.model.User;
import com.otod.server.otod.model.vrss.VrssUser;
import com.otod.server.otod.respository.UserRepository;
import com.otod.server.otod.respository.vrss.VrssUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VrssUserService {
    @Autowired
    private VrssUserRepository vrssUserRepository;
    @Autowired
    private UserRepository userRepository;
    public String getName(Integer user_id){
        return vrssUserRepository.findById(user_id).get().getUser_name();
    }
    public void register(Integer user_id){
        VrssUser vrssUser=new VrssUser();
        vrssUser.setUser_id(user_id);
        User user=userRepository.findById(Long.parseLong(user_id.toString())).get();
        vrssUser.setUser_name(user.getUsername());
        vrssUserRepository.save(vrssUser);
    }
}
