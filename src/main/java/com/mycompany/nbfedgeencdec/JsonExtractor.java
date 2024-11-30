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
  
   public static String extractJsonValue(String input, String key) {
       
        String regex =  Pattern.quote(key)+"\":\"" + "(.*?)\",\"appzillon";
        String regex2 =  Pattern.quote(key)+"\":\"" + "(.*?)}\"";
        // Compile the pattern
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL); // DOTALL allows '.' to match newlines
        Matcher matcher = pattern.matcher(input);

        
        if (matcher.find()) {
            return matcher.group(1); 
        } else {
           Pattern pattern2 = Pattern.compile(regex2, Pattern.DOTALL);
            Matcher matcher2 = pattern2.matcher(input);
            
            // Attempt to find a match using the second regex
            if (matcher2.find()) {
                return matcher2.group(1); // Return the value between the key and '}'
            } else {
                return null; // Return null if neither regex matches
            }
        }
   }
}
