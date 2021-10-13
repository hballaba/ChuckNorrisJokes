package com.hballaba.chucknorris.controllers;

import com.hballaba.chucknorris.dao.JokeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ChuckController {

private JokeDAO jokeDAO;

    @Autowired
    public ChuckController(JokeDAO jokeDAO) {
        this.jokeDAO = jokeDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("joke", jokeDAO.index());
        return "chuck/index";
    }
}
