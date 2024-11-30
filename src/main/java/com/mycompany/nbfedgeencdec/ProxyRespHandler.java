package com.mycompany.nbfedgeencdec;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.proxy.http.InterceptedResponse;
import burp.api.montoya.proxy.http.ProxyResponseHandler;
import burp.api.montoya.proxy.http.ProxyResponseReceivedAction;
import burp.api.montoya.proxy.http.ProxyResponseToBeSentAction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProxyRespHandler implements ProxyResponseHandler {

    private MontoyaApi api;    
    private List<String> keys;
    
    public ProxyRespHandler(MontoyaApi api, List<String> keys) {
        this.api = api;
        this.keys = keys;
    }

    @Override
    public ProxyResponseReceivedAction handleResponseReceived(InterceptedResponse interceptedResponse) {
        try {
            String body = interceptedResponse.bodyToString();
            
            for (String key : keys){
            String payload = JsonExtractor.extractValue(body, key);
               
            if (payload != null && !payload.equals("")) {
//            .replaceAll("(\\{.*?)", "\n$1\n").replaceAll(",", ",\n");
            String updated = AESGCM.Decryption(payload, NicoEncDec.KEY);

            
            body = body.replace(payload, updated);
            }}
           
            HttpResponse response = interceptedResponse.withBody(body);

            return ProxyResponseReceivedAction.continueWith(response);
        } catch (Exception ex) {
            Logger.getLogger(ProxyRespHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ProxyResponseToBeSentAction handleResponseToBeSent(InterceptedResponse interceptedResponse) {
        try {
            String body = interceptedResponse.bodyToString();

           
            for (String key : keys) {
                String payload = JsonExtractor.extractJsonValue(body, key);

                if (payload != null && !payload.equals("")) {
                    
                    String updatedPayload = AESGCM.Encryption(payload, NicoEncDec.KEY);

                    body = body.replace(payload, updatedPayload);
                }
            }

           
            HttpResponse response = interceptedResponse.withBody(body);

            return ProxyResponseToBeSentAction.continueWith(response);
        } catch (Exception ex) {
            Logger.getLogger(ProxyRespHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
