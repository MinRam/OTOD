package com.otod.server.otod.zhy.model;

import javax.persistence.*;
import java.util.Date;

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
    private Long complainer;
    //投诉内容
    @Column(name = "content")
    private String content;
    //被投诉用户（外键）
    @Column(name = "complained_id")
    private Long complained;
    //投诉日期
    @Column(name = "c_date")
    private Date cDate;
    //受理状态
    @Column(name = "c_state")
    private int cState;


    public Long getUserEvalId() {
        return userEvalId;
    }

    public void setUserEvalId(Long userEvalId) {
        this.userEvalId = userEvalId;
    }

    public Long getComplainer() {
        return complainer;
    }

    public void setComplainer(Long complainer) {
        this.complainer = complainer;
    }

    public Long getComplained() {
        return complained;
    }

    public void setComplained(Long complained) {
        this.complained = complained;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public int getcState() {
        return cState;
    }

    public void setcState(int cState) {
        this.cState = cState;
    }

    public UserComplain() {
    }
}
