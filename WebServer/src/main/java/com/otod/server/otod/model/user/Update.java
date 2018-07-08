package com.otod.server.otod.model.user;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "user_update")
public class Update {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "update_id")
    private Long updateId;

    @Column(name = "images")
    private String images;

    @Column(name = "update_content")
    private String content;

    @Column(name = "update_date")
    private Date date;

//    @ManyToOne
//    @JoinColumn(name = "update_type")
//    private ObjectType objectType;

    @ManyToOne
    @JoinColumn(name = "update_sender")
    private UserInfo userSender;

    @ManyToMany
    @JoinTable(
            name = "update_tags",
            joinColumns = @JoinColumn(name = "update_id",referencedColumnName = "update_id"),
            inverseJoinColumns = @JoinColumn(name = "updateTag_id",referencedColumnName = "update_tagId"))
    private List<UpdateTag> tags;

    public Update() {
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserInfo getUserSender() {
        return userSender;
    }

    public void setUserSender(UserInfo userSender) {
        this.userSender = userSender;
    }

//    public ObjectType getObjectType() {
//        return objectType;
//    }
//
//    public void setObjectType(ObjectType objectType) {
//        this.objectType = objectType;
//    }

    public List<UpdateTag> getTags() {
        return tags;
    }

    public void setTags(List<UpdateTag> tags) {
        this.tags = tags;
    }
}
