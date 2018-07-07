package com.otod.server.otod.model.user;

import javax.persistence.*;

@Entity
@Table(name = "objectInfo")
public class ObjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "objectType")
    private Integer id;

    @Column(name="objectName")
    private String name;

    public ObjectType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
