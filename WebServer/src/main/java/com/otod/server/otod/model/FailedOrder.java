package com.otod.server.otod.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhang on 2018/6/5.
 */

@Entity
@Table(name="failed_order")
public class FailedOrder {
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

    @Column(name = "S_date")
    private Date sDate;

    @Column(name = "urgency")
    private String urgency;

    @Column(name = "reason")
    private Date reason;

    @Column(name = "receive_time")
    private Date receiveTime;

    @Column(name = "user_s_id")
    private String userSId;

    @Column(name = "user_r_id")
    private String userRId;

    @Column(name = "profits")
    private int profits;

    @Column(name = "deadline")
    private int deadline;
}
