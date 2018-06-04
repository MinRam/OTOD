package com.otod.server.otod.services;

import com.otod.server.otod.model.User;
import com.otod.server.otod.others.IDWorker.IdGenerator;
import com.otod.server.otod.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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

    @Bean
    private IdGenerator idGenerator(){
        return new IdGenerator(0,0);
    };

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void save(User user){
        if(user.getPassword() == null || user.getUsername() == null)
            return;
//        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setUserId(idGenerator().nextId());
        userRepository.save(user);
    }

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

}