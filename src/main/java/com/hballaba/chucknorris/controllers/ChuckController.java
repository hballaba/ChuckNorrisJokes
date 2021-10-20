package com.hballaba.chucknorris.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.hballaba.chucknorris.dao.JokeDAO;
import com.hballaba.chucknorris.utils.JsonParse;
import com.hballaba.chucknorris.utils.MyConnectionHttp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

@Controller
@RequestMapping("/jokes")
public class ChuckController {




    private JokeDAO jokeDAO;
    private Logger logger = LogManager.getLogger(ChuckController.class);


    @Autowired
    public ChuckController(JokeDAO jokeDAO) {
        this.jokeDAO = jokeDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("jokes", jokeDAO.index());
        return "chuck/index";
    }

    @GetMapping("/new")
    public String GetNewJoke(Model model){
        String urlAddress = "https://api.chucknorris.io/jokes/random";
        String method = "GET";

        HttpURLConnection connection = MyConnectionHttp.HttpURLConnection(urlAddress, method);
        if (connection == null) {
            return "chuck/index";
        }
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            connection.connect();

            if( HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                inputStreamReader = new InputStreamReader(connection.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                String response = bufferedReader.readLine();
                JsonNode json = JsonParse.GetJson(response);
                String value = json.get("value").asText() ;
                model.addAttribute("joke", value);
                logger.info("New joke: " + value);
            }
            else {
                logger.info("URL: " + urlAddress + "connect error");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (inputStreamReader != null)
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return "chuck/newJoke";
    }

    @GetMapping("/{id}")
    public String Show(@PathVariable("id") int id, Model model){
            model.addAttribute("joke", jokeDAO.show(id));
            return "chuck/show";
        }
}
