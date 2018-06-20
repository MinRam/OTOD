package com.otod.server.otod.pojos;

import java.util.Date;

/**
 * Created by zhang on 2018/6/9.
 */
public class PublishOrder {
    private Long id;
    private String title;
    private String content;
    private Date deadline;
    private String urgency;
    private int contributers;

    public PublishOrder(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public int getContributers() {
        return contributers;
    }

    public void setContributers(int contributers) {
        this.contributers = contributers;
    }
}
