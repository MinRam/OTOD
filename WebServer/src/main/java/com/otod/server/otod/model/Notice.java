package com.otod.server.otod.model;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notice_id")
    private Long noticeId;

    @Column(name = "notice_addtime")
    private Date addtime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userOwn;

    @Column(name = "notice_title")
    private String title;

    @Column(name = "notice_object")
    private Integer object;

    @Column(name = "notice_type")
    private Integer type;

    @ManyToOne
    @JoinColumn(name = "notice_origin_user")
    private User userOut;

    @Column(name = "notice_read")
    private Integer read;

    public Notice() {
    }

    public Notice(Long noticeId, User userOwn, Integer type) {
        this.noticeId = noticeId;
        this.userOwn = userOwn;
        this.type = type;
    }


    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
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

    public User getUserOwn() {
        return userOwn;
    }

    public void setUserOwn(User userOwn) {
        this.userOwn = userOwn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public User getUserOut() {
        return userOut;
    }

    public void setUserOut(User userOut) {
        this.userOut = userOut;
    }
}
