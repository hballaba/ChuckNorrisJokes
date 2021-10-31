package com.hballaba.chucknorris.models;

import lombok.Data;

@Data
public class Joke {
    private int id;
    private String joke;
    private String username;

    public Joke(String joke, int userID) {
        this.joke = joke;
        this.username = username;
    }

    public Joke() {

    }
}
