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

    @Column(name = "update_tagImage")
    private String updateImg;

    public UpdateTag() {
    }

    public Long getUpdateTagId() {
        return updateTagId;
    }

    public void setUpdateTagId(Long updateTagId) {
        this.updateTagId = updateTagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
