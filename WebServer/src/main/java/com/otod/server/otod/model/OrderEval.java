package com.otod.server.otod.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhang on 2018/6/5.
 */
//订单评价表
@Entity
@Table(name = "order_eval")
public class OrderEval {
    //订单评价id（主键）
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_eval_id")
    private Long orderEvalId;
    @Column(name = "r_title")
    private String rTitle;
    @Column(name = "r_content")
    private String rContent;
    @Column(name = "s_title")
    private String sTitle;
    @Column(name = "s_content")
    private String sContent;
    //求助方的分数
    @Column(name = "s_num")
    private int sNum;
    //接收方的分数
    @Column(name = "r_num")
    private int rNum;
    //求助方评价日期
    @Column(name = "s_date")
    private Date sDate;
    //接收方评价日期
    @Column(name = "r_date")
    private Date rDate;

    public OrderEval(){}

    public Long getOrderEvalId() {
        return orderEvalId;
    }

    public void setOrderEvalId(Long orderEvalId) {
        this.orderEvalId = orderEvalId;
    }

    public String getrTitle() {
        return rTitle;
    }

    public void setrTitle(String rTitle) {
        this.rTitle = rTitle;
    }

    public String getrContent() {
        return rContent;
    }

    public void setrContent(String rContent) {
        this.rContent = rContent;
    }

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getsContent() {
        return sContent;
    }

    public void setsContent(String sContent) {
        this.sContent = sContent;
    }

    public int getsNum() {
        return sNum;
    }

    public void setsNum(int sNum) {
        this.sNum = sNum;
    }

    public int getrNum() {
        return rNum;
    }

    public void setrNum(int rNum) {
        this.rNum = rNum;
    }

    public Date getsDate() {
        return sDate;
    }

    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }
}
