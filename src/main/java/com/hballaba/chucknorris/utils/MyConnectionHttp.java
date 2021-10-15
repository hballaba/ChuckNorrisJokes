package com.hballaba.chucknorris.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MyConnectionHttp {
    public static HttpURLConnection HttpURLConnection(String urlAddress, String method) {
        URL url = null;
        HttpURLConnection connection = null;

        try {
            url = new URL(urlAddress);
            connection = (HttpURLConnection) url.openConnection();
            System.out.println("connection "  + connection);
            connection.setRequestMethod(method);
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
