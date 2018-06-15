package com.otod.server.otod.model.vrss;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="vrss_download")
public class Download {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer download_id;
    private Date download_time;

    @ManyToOne(targetEntity=VrssUser.class)
    @JoinColumn(name="user_id")
    private VrssUser vrssUser;//外键

    @ManyToOne(targetEntity=FileInfo.class)
    @JoinColumn(name="file_id")
    private FileInfo file;//外键

    public Integer getDownload_id() {
        return download_id;
    }

    public void setDownload_id(Integer download_id) {
        this.download_id = download_id;
    }

    public Date getDownload_time() {
        return download_time;
    }

    public void setDownload_time(Date download_time) {
        this.download_time = download_time;
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
    public FileInfo getFile() {
        return file;
    }
//    @JsonBackReference
    public void setFile(FileInfo file) {
        this.file = file;
    }
}
