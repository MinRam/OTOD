package com.otod.server.otod.model.vrss;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="vrss_tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;

    @ManyToMany(targetEntity = FileInfo.class, mappedBy = "tag")
    private List<FileInfo> file = new ArrayList<>();//外键

    @ManyToMany(targetEntity = FileList.class, mappedBy = "tag")
    private List<FileList> filelist = new ArrayList<>();//外键

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
