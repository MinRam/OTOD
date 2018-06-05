package com.otod.server.otod.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhang on 2018/6/5.
 */

@Entity
@Table(name="comment_order")
public class CommenOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "reply_id")
    private String replyId;

    @Column(name = "type")
    private String type;

    //发布日期
    @Column(name = "S_date")
    private Date sDate;

    @Column(name = "order_state")
    private String orderState;

    @Column(name = "receive_time")
    private Date receiveTime;
    //订单评价
    @Column(name = "order_eval_id")
    private int orderEvalId;
    //发布用户
    @Column(name = "user_s_id")
    private String userSId;
    //接受用户
    @Column(name = "user_r_id")
    private String userRId;
    //订单完成时间
    @Column(name = "complete_time")
    private Date completeTime;

    @Column(name = "urgency")
    private String urgency;

    @Column(name = "profits")
    private int profits;

    @Column(name = "deadline")
    private int deadline;
}
