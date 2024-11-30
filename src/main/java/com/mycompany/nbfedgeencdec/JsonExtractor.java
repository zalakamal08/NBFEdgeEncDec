package com.mycompany.nbfedgeencdec;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JsonExtractor {
   
  public static String extractValue(String input, String key) {
         
        String regex = String.format("(?i)\"%s\"\\s*:\\s*\"([^\"]*)\"", key);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
 
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null; 
        }
    }
}
