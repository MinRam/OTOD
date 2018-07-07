package com.otod.server.otod.model.user;

import javax.persistence.*;

@Entity
@Table(name = "user_update_tag")
public class UpdateTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "update_tagId")
    private Long updateTagId;

    @Column(name = "update_tagName")
    private String tagName;
}
