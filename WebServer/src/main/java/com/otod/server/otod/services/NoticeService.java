package com.otod.server.otod.services;

import com.otod.server.otod.model.Notice;
import com.otod.server.otod.model.User;
import com.otod.server.otod.respository.NoticeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private NoticeRespository noticeRespository;

    /**
     * 获取user未读notice
     * @param user
     * @return
     */
    public List<Notice> getAllNewNotices(User user){
        return noticeRespository.findAllByUserOwnAndRead(user,1);
    }

    @Transactional
    public void readNotices(List<Long> noticeId){
         noticeRespository.updateNoticeStatus(0,noticeId);
    }
}
