package com.otod.server.otod.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by zhang on 2018/6/5.
 */
//用户投诉表
@Entity
@Table(name = "user_complain")
public class UserComplain {
    //用户投诉id（主键）
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_eval_id")
    private Long userEvalId;
    //投诉用户id（外键）
    @Column(name = "complainer_id")
    private Long complainerId;
    //投诉内容
    @Column(name = "content")
    private String content;
    //被投诉用户（外键）
    @Column(name = "complained_id")
    private Long complainedId;
    //投诉日期
    @Column(name = "c_date")
    private Date cDate;
    //投诉订单
    @ManyToOne
    @JoinColumn(name = "complained_order")
    private CommenOrder commenOrder;
    //受理状态
    @Column(name = "c_state")
    private int cState;

    public UserComplain(){}

    public Long getUserEvalId() {
        return userEvalId;
    }

    public void setUserEvalId(Long userEvalId) {
        this.userEvalId = userEvalId;
    }

    public Long getComplainerId() {
        return complainerId;
    }

    public void setComplainerId(Long complainerId) {
        this.complainerId = complainerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getComplainedId() {
        return complainedId;
    }

    public void setComplainedId(Long complainedId) {
        this.complainedId = complainedId;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public CommenOrder getCommenOrder() {
        return commenOrder;
    }

    public void setCommenOrder(CommenOrder commenOrder) {
        this.commenOrder = commenOrder;
    }

    public int getcState() {
        return cState;
    }

    public void setcState(int cState) {
        this.cState = cState;
    }
}
