package com.otod.server.otod.respository;

import com.otod.server.otod.model.user.Update;
import com.otod.server.otod.model.user.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserUpdateRepository extends JpaRepository<Update,Long> {
    Update findByUpdateId(Long id);
    Page<Update> findAll(Pageable pageable);
    Page<Update> findAllByUserSenderIn(List<UserInfo> userInfos,Pageable pageable);
    List<Update> findAllByUserSender(UserInfo userInfo);
    List<Update> findAllByUserSenderIn(List<UserInfo> userInfo);
    Long countByUserSender(UserInfo userInfo);
}
