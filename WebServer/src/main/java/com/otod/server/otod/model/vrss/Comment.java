package com.otod.server.otod.model.vrss;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="vrss_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date create_time;
    private String comment;
    private Double score;
    private Integer love;

    @ManyToOne(targetEntity=VrssUser.class)
    @JoinColumn(name="user_id")
    private VrssUser vrssUser;//外键

    @ManyToOne(targetEntity=VrssUser.class)
    @JoinColumn(name="reply_id")
    private VrssUser reply;//外键

    @ManyToOne(targetEntity=FileInfo.class)
    @JoinColumn(name="file_id")
    private FileInfo file;//外键

    @ManyToOne(targetEntity=FileList.class)
    @JoinColumn(name="filelist_id")
    private FileList filelist;//外键

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getLove() {
        return love;
    }

    public void setLove(Integer love) {
        this.love = love;
    }
//    @JsonBackReference
    public VrssUser getVrssUser() {
        return vrssUser;
    }
//    @JsonBackReference
    public void setVrssUser(VrssUser vrssUser) {
        this.vrssUser = vrssUser;
    }
//    @JsonBackReference
    public VrssUser getReply() {
        return reply;
    }
//    @JsonBackReference
    public void setReply(VrssUser reply) {
        this.reply = reply;
    }
//    @JsonBackReference
    public FileInfo getFile() {
        return file;
    }
//    @JsonBackReference
    public void setFile(FileInfo file) {
        this.file = file;
    }
//    @JsonBackReference
    public FileList getFilelist() {
        return filelist;
    }
//    @JsonBackReference
    public void setFilelist(FileList filelist) {
        this.filelist = filelist;
    }
}
