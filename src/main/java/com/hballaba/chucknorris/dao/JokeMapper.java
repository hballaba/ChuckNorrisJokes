package com.hballaba.chucknorris.dao;

import com.hballaba.chucknorris.models.Joke;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JokeMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Joke joke = new Joke();
        joke.setId(resultSet.getInt("id"));
        joke.setJoke(resultSet.getString("joke"));
        joke.setUserID(resultSet.getInt("user_id"));
//        System.out.println("test");
        return joke;
    }
}
