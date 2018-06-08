package com.otod.server.otod.pojos;

import com.otod.server.otod.model.Notice;
import com.otod.server.otod.model.UserInfo;

import java.util.Date;

public class NoticePojo {
    private Long noticeId;
    private Date addtime;
    private String title;
    private UserSimpleInfo userOut;
    private Integer object;
    private Integer type;

    public NoticePojo(Notice notice,UserInfo userInfo){
        this.noticeId = notice.getNoticeId();
        this.addtime = notice.getAddtime();
        this.object = notice.getObject();
        this.type = notice.getType();
        this.userOut =  new UserSimpleInfo(userInfo);;
    }

    public NoticePojo(Long noticeId, Date addtime, String title,UserInfo userInfo, Integer object, Integer type) {
        this.noticeId = noticeId;
        this.addtime = addtime;
        this.title = title;
        this.userOut =  new UserSimpleInfo(userInfo);;
        this.object = object;
        this.type = type;
    }



    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserSimpleInfo getUserOut() {
        return userOut;
    }

    public void setUserOut(UserSimpleInfo userOut) {
        this.userOut = userOut;
    }

    public Integer getObject() {
        return object;
    }

    public void setObject(Integer object) {
        this.object = object;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
