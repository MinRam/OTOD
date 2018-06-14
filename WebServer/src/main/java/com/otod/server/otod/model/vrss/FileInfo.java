package com.otod.server.otod.model.vrss;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="vrss_file")
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String file_url;
    private Date uploadTime;
    private Integer downloadCnt;
    private Double size;
    private String type;
    private Double score;
    private String description;
    private Integer views;
    private Integer love;

    @ManyToOne(targetEntity=VrssUser.class)
    @JoinColumn(name="uploader")
    private VrssUser vrssUser;

    @ManyToMany(targetEntity = VrssUser.class, mappedBy = "file")
    private List<VrssUser> userlike = new ArrayList<VrssUser>();//外键

    @ManyToMany(targetEntity = FileList.class, mappedBy = "file")
    private List<FileList> filelist = new ArrayList<FileList>();//外键

    @ManyToMany(targetEntity=Tag.class)
    @JoinTable(name="vrss_file_tag",joinColumns=
    @JoinColumn(name="file_id"),inverseJoinColumns=
    @JoinColumn(name="tag_id"))
    private List<Tag> tag = new ArrayList<Tag>();//外键

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

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getDownloadCnt() {
        return downloadCnt;
    }

    public void setDownloadCnt(Integer downloadCnt) {
        this.downloadCnt = downloadCnt;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public List<FileList> getFilelist() {
        return filelist;
    }
    @JsonBackReference
    public void setFilelist(List<FileList> filelist) {
        this.filelist = filelist;
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
