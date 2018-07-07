package com.otod.server.otod.respository;

import com.otod.server.otod.model.user.User;
import com.otod.server.otod.model.user.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFollowRespository extends JpaRepository<UserFollow,Integer>{
    List<UserFollow> findAllByUser(User user);
    List<UserFollow> findAllByUserFollow(User user);
    UserFollow findAllByUserAndUserFollow(User user,User userFollow);
}
