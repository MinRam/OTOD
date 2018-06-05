package com.otod.server.otod.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(generator = "IdGenerator")
    @GenericGenerator(name = "IdGenerator", strategy = "com.otod.server.otod.others.IDWorker.IdGenerator")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "identity_type")
    private String identityType;

    @Column(name="identifier")
    private String username;

    @JsonIgnore
    @Column(name ="credential")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Role> roles;

    public User() {
    }

    public User(String identityType,String username, String password) {
        this.username = username;
        this.password = password;
        this.identityType = identityType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getIdentity_type() {
        return identityType;
    }

    public void setIdentity_type(String identity_type) {
        this.identityType = identity_type;
    }

    @Override
    public String toString() {
        return "ID:" + this.userId
                + ",Name:"+ this.username
                + ",Password:"+this.password;
    }
}
