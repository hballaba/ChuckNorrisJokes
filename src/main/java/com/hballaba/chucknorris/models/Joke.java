package com.hballaba.chucknorris.models;

import lombok.Data;

@Data
public class Joke {
    private int id;
    private String joke;
    private int user_id;

    public Joke(String joke, int userID) {
        this.joke = joke;
        this.user_id = user_id;
    }

    public Joke() {

    }
}
