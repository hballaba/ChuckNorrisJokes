package com.hballaba.chucknorris.models;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public int getUserID() {
        return user_id;
    }

    public void setUserID(int userID) {
        this.user_id = userID;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "id=" + id +
                ", joke='" + joke + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
