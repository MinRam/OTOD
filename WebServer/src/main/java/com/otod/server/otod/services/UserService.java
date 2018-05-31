package com.otod.server.otod.services;

import com.otod.server.otod.model.User;
import com.otod.server.otod.others.IDWorker.IdGenerator;
import com.otod.server.otod.respository.UseJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UseJPA userJPA;

    public List<User> getAllUsers(){
        return userJPA.findAll();
    }

    public void registeUser(User user){
        IdGenerator idGenerator = new IdGenerator(0,0);

        user.setUserId(idGenerator.nextId());

        userJPA.save(user);
    }
}
