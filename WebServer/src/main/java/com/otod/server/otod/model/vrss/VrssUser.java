package com.otod.server.otod.model.vrss;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="vrss_user")
public class VrssUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String user_name;

    @ManyToMany(targetEntity=FileInfo.class)
    @JoinTable(name="vrss_userlike_file",joinColumns=
    @JoinColumn(name="user_id"),inverseJoinColumns=
    @JoinColumn(name="file_id"))
    private List<FileInfo> file = new ArrayList<>();//外键

    @ManyToMany(targetEntity=FileList.class)
    @JoinTable(name="vrss_userlike_filelist",joinColumns=
    @JoinColumn(name="user_id"),inverseJoinColumns=
    @JoinColumn(name="filelist_id"))
    private List<FileList> filelist = new ArrayList<>();//外键

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
    public List<FileList> getFilelist() {
        return filelist;
    }
    @JsonBackReference
    public void setFilelist(List<FileList> filelist) {
        this.filelist = filelist;
    }
}
