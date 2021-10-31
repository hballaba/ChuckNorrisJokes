package com.hballaba.chucknorris.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Component
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="username is not be empty")
//    @Size(min = 2, max = 30, message = "invalid size username")
    private String username;

    @Size(min=2, message = "the password must be at least 5 characters")
    private String password;

    //Не отображается в базе
    @Transient
    private String confirmPassword;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
}

/*
CREATE TABLE users (
    id          bigserial,
    username    varchar(30),
    password    varchar(80) not null,
    #   email       varchar(50) unique,
    primary key (id)
    );

 */