package com.mycompany.nbfedgeencdec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

 
public class Test {
    
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        
            String body2 = """
                           {"appzillonHeader":"990a5225299c0857825ca922RqcB3cyJajeID0IbAz/4z2lhBlmWMe8OBYg7KMZpyMZa457m1nDKl7KxRu5mQPPGb3L+G0c+yrhZbgooJZgViPfZxGHnSCSndZWaTEps5/hBwSx+7d/eT1/9M9oily5EUJIklSSB2WL58gTeIs+FhJP5WAimRoi4TOpMC+NZKLMg2uPyXH3R6EUB16zn6DicbdKI/SZcsLCzCgxUgQ76TI6Wm0g+e02vjNNJ0OMvVFHP8EOBugrJBjF7JjpgDe5ik+ZSiW/5mBDvwEkGj7+hH6QhyNU+tegQ3FDqCRsPnnza",
                           "appzillonBody":"990a5225299c0857825ca922RqcB3cy6Z3neQkI+ADjV2TseQUmnLfcCBJUgKZUmw8BCrYi/kHTMjpiwLbBmFLP3WVy6FRgxIUvuqueLaY+xKelg2oVYXw=="
                           }""";
            Object json = objectMapper.readTree(body2.substring(1,-1)); // Convert string to JSON tree

            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            
            System.out.println(prettyJson);
    }
    
}
