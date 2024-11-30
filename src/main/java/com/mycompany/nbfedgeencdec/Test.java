package com.mycompany.nbfedgeencdec;
import java.util.regex.*;

public class Test {
    
    // Function to extract value based on key and specific format
    public static String extractValue(String input, String key) {
        // Regular expression to extract value between key":" and ","appzillon
        String regex = String.format("\"%s\"\\s*:\\s*\"(.*?)\",\"appzillon\"", key);
        
        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        
        // If a match is found, return the captured value
        if (matcher.find()) {
            return matcher.group(1); // Return the captured value between key":" and ","appzillon
        } else {
            return null; // Return null if the key is not found or no match is found
        }
    }

    public static void main(String[] args) {
        // Example JSON string with the "key":"value","appzillon" format
        String jsonResponse = "\\{\\\"appzillonHeader\\\":\\\"\\{\\\"appId\\\":\\\"nbfslf\\\",\\\"deviceId\\\":\\\"WEB\\\",\\\"requestKey\\\":\\\"1.8967678756544442\\\",\\\"screenId\\\":\\\"\\\",\\\"sessionId\\\":\\\"Ziojunbw197urtIujujUUU8768768768\\\",\\\"status\\\":true,\\\"userId\\\":\\\"+971509632145_test@test.com\\\",\\\"interfaceId\\\":\\\"appzillonMailRequest\\\",\\\"source\\\":\\\"APPZILLON\\\",\\\"requestId\\\":\\\"PROC_7\\\",\\\"location\\\":\\{\\\"lat\\\":\\\"0\\\",\\\"lng\\\":\\\"0\\\"\\},\\\"clientNonce\\\":\\\"lEgHhPoPeo\\\",\\\"reqRefId\\\":\\\"6a0e9194-21d0-4e6d-beb7-4bea1cfe39aa\\\",\\\"appVersion\\\":\\\"\\\"\\}\\\",\\\"appzillonBody\\\":\\\"\\{\\\"loginResponse\\\":\\{\\\"status\\\":true,\\\"userDet\\\":\\{\\\"OTPRequired\\\":\\\"Y\\\",\\\"lastLogin\\\":\\\"\\\",\\\"extId\\\":\\\"C\\\",\\\"id\\\":\\\"+971509632145_test@test.com\\\",\\\"profilePic\\\":\\\"\\\",\\\"serverSessionTimeOut\\\":3000,\\\"userPrefs\\\":\\{\\\"dateFormat\\\":\\\"dd-MMM-yyyy\\\",\\\"numberMask\\\":\\\"MILLION\\\",\\\"language\\\":\\\"en\\\",\\\"dateTimeFormat\\\":\\\"dd-MMM-yyyy  hh:mm:ss\\\",\\\"longitude\\\":\\\"77.6196951\\\",\\\"lattitude\\\":\\\"12.9255616\\\",\\\"theme\\\":\\\"CorporateOnboarding\\\",\\\"baseThemesMap\\\":\\\"\\{\\\\\"CorporateOnboarding\\\\\":\\\\\"NBFONB\\\\\"\\}\\\",\\\"decimalSep\\\":\\\".\\\",\\\"otaReqd\\\":\\\"N\\\"\\}\\},\\\"addInfo1\\\":\\\"USER\\\",\\\"addInfo2\\\":\\\"\\\",\\\"addInfo3\\\":\\\"509632145\\\",\\\"addInfo4\\\":\\\"971\\\"\\}\\}\\\",\\\"appzillonSafe\\\":\\\"\\\"\\}";
      
        
        
        String appzillonHeader = extractValue(jsonResponse, "appzillonHeader");
        System.out.println("appzillonHeader: " + appzillonHeader);
        
       
    }
}
