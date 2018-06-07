package com.otod.server.otod.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhang on 2018/6/5.
 */
//回复表
@Entity
@Table(name = "reply_comment")
public class ReplyComment {
    //回复id（主键）
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reply_id")
    private Long reply_id;
    //回复时间
    @Column(name = "create_time")
    private Date createTime;
    //回复内容
    @Column(name = "comment")
    private String comment;
    //订单id（外键）
    @Column(name = "order_user_id")
    private Long orderUser;
    //回复用户id（外键）
    @Column(name = "reply_user_id")
    private Long replyUser;

    public ReplyComment() {
    }

    public Long getReply_id() {
        return reply_id;
    }

    public void setReply_id(Long reply_id) {
        this.reply_id = reply_id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(Long orderUser) {
        this.orderUser = orderUser;
    }

    public Long getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(Long replyUser) {
        this.replyUser = replyUser;
    }
}
