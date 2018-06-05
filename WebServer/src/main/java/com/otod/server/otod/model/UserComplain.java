package com.otod.server.otod.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhang on 2018/6/5.
 */

@Entity
@Table(name = "user_complain")
public class UserComplain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_eval_id")
    private Long userEvalId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "content")
    private String content;

    @Column(name = "user_evd_id")
    private Long userEvdId;

    @Column(name = "c_date")
    private Date cDate;

    @Column(name = "c_state")
    private int cState;
}
