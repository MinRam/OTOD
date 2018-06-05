package com.otod.server.otod.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhang on 2018/6/5.
 */

@Entity
@Table(name = "order_eval")
public class OrderEval {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_eval_id")
    private Long orderEvalId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "content")
    private String content;

    @Column(name = "s_num")
    private int sNum;

    @Column(name = "r_num")
    private int rNum;

    @Column(name = "s_date")
    private Date sDate;

    @Column(name = "r_date")
    private Date rDate;
}
