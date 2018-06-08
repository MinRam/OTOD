package com.otod.server.otod.pojos;

import java.util.List;

public class UserFollowList {
    // 自己关注的
    private List<UserSimpleInfo> userFollow;
    // 自己被关注的
    private List<UserSimpleInfo> userFollowed;

    public UserFollowList() {
    }

    public UserFollowList(List<UserSimpleInfo> userFollow, List<UserSimpleInfo> userFollowed) {
        this.userFollow = userFollow;
        this.userFollowed = userFollowed;
    }

    public List<UserSimpleInfo> getUserFollow() {
        return userFollow;
    }

    public void setUserFollow(List<UserSimpleInfo> userFollow) {
        this.userFollow = userFollow;
    }

    public List<UserSimpleInfo> getUserFollowed() {
        return userFollowed;
    }

    public void setUserFollowed(List<UserSimpleInfo> userFollowed) {
        this.userFollowed = userFollowed;
    }
}
