package com.hballaba.chucknorris.dao;

import com.hballaba.chucknorris.models.Joke;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JokeDAO {

    private JdbcTemplate jdbcTemplate;
    private Logger logger = LogManager.getLogger(JokeDAO.class);

    @Autowired
    public JokeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Joke> index() {
          String query = "SELECT * FROM jokes";

        logger.info(query);
        List<Joke> jokes = jdbcTemplate.query(query, new JokeMapper());
        logger.info(jokes);
        return jokes;
    }

    public void save(Joke joke) {
        String query = "INSERT INTO jokes (joke, user_id VALUES ('test', 2)";
        jdbcTemplate.update(query, new JokeMapper());
    }

    public Joke show(int id) {
        String query = "SELECT * FROM jokes WHERE id=?";
        return (Joke) jdbcTemplate.query(query, new Object[]{id}, new JokeMapper())
                .stream().findAny().orElse(null);
    }

}
