package com.mycompany.nbfedgeencdec;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.proxy.http.InterceptedRequest;
import burp.api.montoya.proxy.http.ProxyRequestHandler;
import burp.api.montoya.proxy.http.ProxyRequestReceivedAction;
import burp.api.montoya.proxy.http.ProxyRequestToBeSentAction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProxyReqHandler implements ProxyRequestHandler{
    public MontoyaApi api;
    public String key1 ="appzillonHeader";
    public String key2 ="appzillonBody";
    
    
    public ProxyReqHandler(MontoyaApi api){
        this.api=api;
    }
    
    @Override
    public ProxyRequestReceivedAction handleRequestReceived(InterceptedRequest ir) {
        
        try {
            String body = ir.bodyToString();
            
            String payload1 = JsonExtractor.extractValue(body,key1);
            String payload2 = JsonExtractor.extractValue(body,key2);
            
            String updated1 = AESGCM.Decryption(payload1, NicoEncDec.KEY) ;
            String updated2 = AESGCM.Decryption(payload2, NicoEncDec.KEY);
            
                     
            String body2 = body.replace(payload1, updated1);
            body2 = body2.replace(payload2, updated2);
          
            
            ObjectMapper objectMapper = new ObjectMapper();
            Object json = objectMapper.readTree(body2.substring(1,-1)); // Convert string to JSON tree

            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
                       
            HttpRequest request = ir.withBody(prettyJson);
            
            return  ProxyRequestReceivedAction.continueWith(request) ;
        } catch (Exception ex) {
            Logger.getLogger(ProxyReqHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ProxyRequestToBeSentAction handleRequestToBeSent(InterceptedRequest ir) {
        
        try {
            String body = ir.bodyToString();
            
            String payload3 = JsonExtractor.extractValue(body,key1);
            String payload4 = JsonExtractor.extractValue(body,key2);
            
            
            String updated3 = AESGCM.Encryption(payload3, NicoEncDec.KEY);
            String updated4 = AESGCM.Encryption(payload4, NicoEncDec.KEY);
            
            
            String body2 = body.replace(payload3, updated3);
            body2 = body2.replace(payload4, updated4);
            HttpRequest request = ir.withBody(body2);
            
            return ProxyRequestToBeSentAction.continueWith(request);
        } catch (Exception ex) {
            Logger.getLogger(ProxyReqHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}
