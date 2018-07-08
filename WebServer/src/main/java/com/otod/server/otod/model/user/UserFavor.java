package com.otod.server.otod.model.user;

import javax.persistence.*;

@Entity
@Table(name = "user_favor")
public class UserFavor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "favor_id")
    private Long favorID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "update_liked")
    private Update update;

    public UserFavor() {
    }

    public UserFavor(User user, Update update) {
        this.user = user;
        this.update= update;
    }

    public Long getFavorID() {
        return favorID;
    }

    public void setFavorID(Long favorID) {
        this.favorID = favorID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }
}
