package com.otod.server.otod.model.user;

import javax.persistence.*;

@Entity
@Table(name = "user_follow")
public class UserFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "follow_id")
    private Integer followId;

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

    public Integer getFollowId() {
        return followId;
    }

    public void setFollowId(Integer followId) {
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
