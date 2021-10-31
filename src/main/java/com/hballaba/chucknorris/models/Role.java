package com.hballaba.chucknorris.models;

import lombok.Data;
import org.springframework.stereotype.Component;


import javax.persistence.*;

//@Component
@Data
@Table(name = "users")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}


/*
CREATE TABLE roles (
    id          serial,
    name    varchar(30),
primary key (id)
);

CREATE TABLE user_roles (
user_id         bigint not null,
role_id         int not null,
primary key (user_id, role_id),
foreign key (user_id) references users (id),
foreign key (role_id) references roles (id)
);

 */