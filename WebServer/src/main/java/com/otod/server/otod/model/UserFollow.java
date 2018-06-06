package com.otod.server.otod.model;

import javax.persistence.*;

@Entity
@Table(name = "user_follow")
public class UserFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long followId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "followuser_id")
    private User userFollow;

    public UserFollow() {
    }

    public UserFollow(User user, User userFollow) {
        this.user = user;
        this.userFollow = userFollow;
    }

    public Long getFollowId() {
        return followId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUserFollow() {
        return userFollow;
    }

    public void setUserFollow(User userFollow) {
        this.userFollow = userFollow;
    }
}
