package com.otod.server.otod.pojos;

public class UserSimpleInfo {
    private String nickname;
    private String telephone;
    private String headImage;

    public UserSimpleInfo() {
    }

    public UserSimpleInfo(String nickname, String telphone, String headImage) {
        this.nickname = nickname;
        this.telephone = telphone;
        this.headImage = headImage;
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

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
}
