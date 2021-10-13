package com.hballaba.chucknorris.dao;

import com.hballaba.chucknorris.models.Joke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JokeDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JokeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Joke> index() {
        List<Joke> jokes = jdbcTemplate.query("SELECT * FROM jokes", new JokeMapper());
        for(Joke joke : jokes)
            System.out.println("here " + joke);
        return jokes;
    }

}
