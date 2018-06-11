package com.otod.server.otod.respository;

import com.otod.server.otod.model.User;
import com.otod.server.otod.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRespository extends JpaRepository<UserInfo,Long> {
    UserInfo findByUser(User user);
    UserInfo findByNickname(String nickname);
}
