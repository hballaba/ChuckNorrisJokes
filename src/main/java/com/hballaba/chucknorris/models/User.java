package com.hballaba.chucknorris.models;

import lombok.Data;

@Data
public class User {
    private String login;
    private String password;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
