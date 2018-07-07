package com.otod.server.otod.respository;

import com.otod.server.otod.model.user.UserInfo;
import com.otod.server.otod.pojos.UserUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserUpdateRepository extends JpaRepository<UserUpdate,Long> {
    Page<UserUpdate> findByUserInfo(UserInfo userInfo,Pageable pageable);
}
