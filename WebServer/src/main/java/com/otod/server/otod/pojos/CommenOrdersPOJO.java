package com.otod.server.otod.pojos;

/**
 * Created by zhang on 2018/6/7.
 */
public class CommenOrdersPOJO {
    private String title;
    private String content;
    private String user_name;
    private int contributers;
    private int emergency;

    public CommenOrdersPOJO(){

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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getContributers() {
        return contributers;
    }

    public void setContributers(int contributers) {
        this.contributers = contributers;
    }

    public int getEmergency() {
        return emergency;
    }

    public void setEmergency(int emergency) {
        this.emergency = emergency;
    }
}
