package com.otod.server.otod.model.vrss;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="vrss_filelist")
public class FileList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date create_time;
    private String description;
    private Double score;
    private Integer views;
    private Integer love;

    @ManyToOne(targetEntity=VrssUser.class)
    @JoinColumn(name="creator_id")
    private VrssUser vrssUser;

    @ManyToMany(targetEntity = VrssUser.class, mappedBy = "filelist")
    private List<VrssUser> userlike = new ArrayList<>();//外键

    @ManyToMany(targetEntity=FileInfo.class)
    @JoinTable(name="vrss_filelist_file",joinColumns=
    @JoinColumn(name="filelist_id"),inverseJoinColumns=
    @JoinColumn(name="file_id"))
    private List<FileInfo> file = new ArrayList<>();//外键

    @ManyToMany(targetEntity=Tag.class)
    @JoinTable(name="vrss_filelist_tag",joinColumns=
    @JoinColumn(name="filelist_id"),inverseJoinColumns=
    @JoinColumn(name="tag_id"))
    private List<Tag> tag = new ArrayList<>();//外键

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
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
    @JsonBackReference
    public List<VrssUser> getUserlike() {
        return userlike;
    }
    @JsonBackReference
    public void setUserlike(List<VrssUser> userlike) {
        this.userlike = userlike;
    }
    @JsonBackReference
    public List<FileInfo> getFile() {
        return file;
    }
    @JsonBackReference
    public void setFile(List<FileInfo> file) {
        this.file = file;
    }
    @JsonBackReference
    public List<Tag> getTag() {
        return tag;
    }
    @JsonBackReference
    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }
}

