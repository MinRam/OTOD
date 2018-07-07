package com.otod.server.otod.respository;

import com.otod.server.otod.model.user.Update;
import com.otod.server.otod.model.user.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserUpdateRepository extends JpaRepository<Update,Long> {
    Page<Update> findAll(Pageable pageable);
    List<Update> findAllByUserSender(UserInfo userSender);
}
