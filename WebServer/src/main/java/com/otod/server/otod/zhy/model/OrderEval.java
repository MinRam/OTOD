package com.otod.server.otod.zhy.model;

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
    //评价对应的订单
    @Column(name = "order_id")
    private Long evdOrder;

    @Column(name = "content")
    private String content;
    //求助方的分数



    public Long getOrderEvalId() {
        return orderEvalId;
    }

    public void setOrderEvalId(Long orderEvalId) {
        this.orderEvalId = orderEvalId;
    }

    public Long getEvdOrder() {
        return evdOrder;
    }

    public void setEvdOrder(Long evdOrder) {
        this.evdOrder = evdOrder;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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


}
