package com.otod.server.otod.respository;

import com.otod.server.otod.model.User;
import com.otod.server.otod.model.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFollowRespository extends JpaRepository<UserFollow,Integer>{
    List<UserFollow> findAllByUser(User user);
    List<UserFollow> findAllByUserFollow(User user);
}
