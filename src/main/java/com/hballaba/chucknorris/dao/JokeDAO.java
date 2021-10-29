package com.hballaba.chucknorris.dao;

import com.hballaba.chucknorris.models.Joke;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

@Component
public class JokeDAO {

    private JdbcTemplate jdbcTemplate;
    private Logger logger = LogManager.getLogger(JokeDAO.class);

    @Autowired
    public JokeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Joke> index(Principal principal) {
        logger.info("User: " + principal.getName());
          String query = "SELECT * FROM jokes WHERE username=?";

        logger.info(query);
        List<Joke> jokes = jdbcTemplate.query(query, new Object[]{principal.getName()}, new JokeMapper());
        logger.info(jokes);
        return jokes;
    }

    public void save(Joke joke) {
        String query = "INSERT INTO jokes (joke, user_id) VALUES (?, ?)";
        logger.info("Method save: " + query + " " + joke);

        jdbcTemplate.update(query, joke.getJoke(), 2);
    }

    public Joke show(int id) {
        String query = "SELECT * FROM jokes WHERE id=?";
        logger.info(query);
        return (Joke) jdbcTemplate.query(query, new Object[]{id}, new JokeMapper())
                .stream().findAny().orElse(null);
    }

}
