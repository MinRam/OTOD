package com.otod.server.otod.respository;

import com.otod.server.otod.model.Notice;
import com.otod.server.otod.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRespository extends JpaRepository<Notice,Long> {

    List<Notice> findAllByUserOwnAndRead(User user,Integer read);

    @Modifying
    @Query("update Notice a set a.read = ?1 where a.noticeId in ?2")
    void updateNoticeStatus(Integer status,List<Long> noticeId);
}
