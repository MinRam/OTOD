package com.otod.server.otod.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otod.server.otod.model.Market_user;
import com.otod.server.otod.model.user.UserInfo;
@Repository
public interface MURepository extends JpaRepository<Market_user, Integer>{
	Market_user findByUserInfo(UserInfo userInfo);
}
