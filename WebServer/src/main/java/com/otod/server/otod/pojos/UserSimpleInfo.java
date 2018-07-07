package com.otod.server.otod.pojos;

import com.otod.server.otod.model.user.UserInfo;

public class UserSimpleInfo {
    private String nickname;
    private String telephone;
    private String headImage;

    public UserSimpleInfo() {
    }

    public UserSimpleInfo(UserInfo userInfo) {
        this.nickname = userInfo.getNickname();
        this.telephone = userInfo.getTelphone();
        this.headImage = userInfo.getHeadImage();
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
