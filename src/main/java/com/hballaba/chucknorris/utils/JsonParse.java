package com.hballaba.chucknorris.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParse {

    public static ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
//        configuration
        return objectMapper;
    }

    public static JsonNode GetJson(String source) throws JsonProcessingException {
        ObjectMapper objectMapper = getObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(source);
        return jsonNode;
    }

}
