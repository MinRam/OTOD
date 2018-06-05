package com.otod.server.otod.pojos;

public class UserSimpleInfo {
    private String nickname;
    private String telephone;
    private String headphone;

    public UserSimpleInfo() {
    }

    public UserSimpleInfo(String nickname, String telphone, String userHphoto) {
        this.nickname = nickname;
        this.telephone = telphone;
        this.headphone = userHphoto;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getHeadphone() {
        return headphone;
    }

    public void setHeadphone(String headphone) {
        this.headphone = headphone;
    }
}
