package com.mycompany.nbfedgeencdec;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.proxy.http.InterceptedRequest;
import burp.api.montoya.proxy.http.ProxyRequestHandler;
import burp.api.montoya.proxy.http.ProxyRequestReceivedAction;
import burp.api.montoya.proxy.http.ProxyRequestToBeSentAction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProxyReqHandler implements ProxyRequestHandler{
    public MontoyaApi api;
    private List<String> keys;
    
    
    public ProxyReqHandler(MontoyaApi api, List<String> keys){
        this.api=api;
        this.keys=keys;
    }
    
    @Override
    public ProxyRequestReceivedAction handleRequestReceived(InterceptedRequest ir) {
        
        try {
            String body = ir.bodyToString();
             for (String key : keys){
            String payload = JsonExtractor.extractValue(body,key);
            
            if (payload != null && !payload.equals("")) {
//                 api.logging().logToOutput(key+" : "+payload );
//            .replaceAll("(\\{.*?)", "\n$1\n").replaceAll(",", ",\n");
            String updated = AESGCM.Decryption(payload, NicoEncDec.KEY).replaceAll("(\\{.*?)", "\n$1\n").replaceAll("\",", "\",\n");
            body = body.replace(payload, updated);
            }
             }
            HttpRequest request = ir.withBody(body);
            
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
            for (String key : keys){
            
            String payload = JsonExtractor.extractJsonValue(body,key).replaceAll("\n$1\n","(\\{.*?)").replaceAll( "\",\n","\",");
            
            if (payload != null && !payload.equals("")) {
//            api.logging().logToOutput(key+" : "+payload +"\n==================================");
            String updated = AESGCM.Encryption(payload, NicoEncDec.KEY);

            body = body.replace(payload, updated);
            }}
            HttpRequest request = ir.withBody(body);
            
            return ProxyRequestToBeSentAction.continueWith(request);
        } catch (Exception ex) {
            Logger.getLogger(ProxyReqHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}
