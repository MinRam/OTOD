package com.otod.server.otod.model;

import com.sun.org.apache.regexp.internal.RE;
import org.aspectj.weaver.ast.Or;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhang on 2018/6/5.
 */
//用户帖子表
@Entity
@Table(name="comment_order")
public class CommenOrder {
    //帖子ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private Long id;
    //订单主题
    @Column(name = "title")
    private String title;
    //订单内容
    @Column(name = "content")
    private String content;
    //主题类型
    @Column(name = "type")
    private String type;
    //发布日期
    @Column(name = "S_date")
    private Date sDate;
    //订单状态
    @Column(name = "order_state")
    private String orderState;
    //接收时间
    @Column(name = "receive_time")
    private Date receiveTime;
    //发布用户（外键）
    @ManyToOne
    @JoinColumn(name = "user_s_id")
    private User user_s;
//    @Column(name = "user_s_id")
//    private String userSId;
    //接受用户（外键）
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<User> user_r;
//    @Column(name = "user_r_id")
//    private String userRId;
    //订单完成时间
    @Column(name = "complete_time")
    private Date completeTime;
    //紧急程度 （1-10）
    @Column(name = "urgency")
    private String urgency;
    //报酬
    @Column(name = "profits")
    private int profits;
    //订单截至日期（小时）
    @Column(name = "deadline")
    private int deadline;
    //失败原因
    @Column(name = "reason")
    private String reason;

    @OneToOne
    private OrderEval orderEval;


    public CommenOrder(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getsDate() {
        return sDate;
    }

    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public User getUser_s() {
        return user_s;
    }

    public void setUser_s(User user_s) {
        this.user_s = user_s;
    }

    public List<User> getUser_r() {
        return user_r;
    }

    public void setUser_r(List<User> user_r) {
        this.user_r = user_r;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public int getProfits() {
        return profits;
    }

    public void setProfits(int profits) {
        this.profits = profits;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public OrderEval getOrderEval() {
        return orderEval;
    }

    public void setOrderEval(OrderEval orderEval) {
        this.orderEval = orderEval;
    }
}
