package com.otod.server.otod.respository;

import com.otod.server.otod.model.Notice;
import com.otod.server.otod.model.Role;
import com.otod.server.otod.model.User;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRespository extends JpaRepository<Notice,Long> {

    List<Notice> findAllByUserOwn(User user);
}
