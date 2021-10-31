package com.hballaba.chucknorris.dao;

import com.hballaba.chucknorris.models.Role;
import com.hballaba.chucknorris.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserDAO {
    private JdbcTemplate jdbcTemplate;
    private Logger logger = LogManager.getLogger(UserDAO.class);

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean save(User user) {
        String queryCheckExistUser = "Select username from users where username=?";
        List<User> existUser = jdbcTemplate.query(queryCheckExistUser, new Object[]{user.getUsername()}, new BeanPropertyRowMapper<>(User.class));
        logger.info(queryCheckExistUser + " " + existUser);
        if(!existUser.isEmpty())
            return true;
        user.setPassword("{bcrypt}" + bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        String querySaveUser = "INSERT INTO users (username, password, enabled)" +
                "Values (?, ?, ?)";

        String query = "INSERT INTO authorities (username, authority) Values (?, ?)";
        logger.info("User: " + user);
        jdbcTemplate.update(querySaveUser, user.getUsername(), user.getPassword(), true);
        jdbcTemplate.update(query, user.getUsername(), "ROLE_USER");
        return false;
    }
}
