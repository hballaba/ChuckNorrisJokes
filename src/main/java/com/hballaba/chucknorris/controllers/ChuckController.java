package com.hballaba.chucknorris.controllers;

import com.hballaba.chucknorris.dao.JokeDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

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
        URL url = null;
        HttpURLConnection connection = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            url = new URL(urlAddress);
            connection = (HttpURLConnection) url.openConnection();
            System.out.println("connection "  + connection);
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            connection.connect();

            if(HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                inputStreamReader = new InputStreamReader(connection.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                String response = bufferedReader.readLine();
                System.out.println(response);
            }
            else {
                logger.info("URL: " + urlAddress + "connect error");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "chuck/newJoke";
    }
}
