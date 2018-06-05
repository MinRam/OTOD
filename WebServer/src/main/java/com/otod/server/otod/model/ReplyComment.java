package com.otod.server.otod.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhang on 2018/6/5.
 */

@Entity
@Table(name = "reply_comment")
public class ReplyComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reply_id")
    private Long reply_id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "comment")
    private String comment;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "reply_user_id")
    private Long replyUserId;
}
