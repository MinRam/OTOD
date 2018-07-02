package com.otod.server.otod.model.user;


import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "role_id")
        private Long id;

        @Column(name = "role_name")
        private String name;

        Role() {}

        public Role(String name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}

