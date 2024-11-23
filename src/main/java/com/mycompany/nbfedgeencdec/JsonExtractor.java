package com.mycompany.nbfedgeencdec;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonExtractor {

    public static String extractValue(String jsonString, String key) {
        try {
            
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode valueNode = rootNode.get(key);
            if (valueNode != null) {
                return valueNode.asText();
            } else {
                return null;  
            }
        } catch (JsonProcessingException e) {
            return null;  
        }
    }

   
   
}
