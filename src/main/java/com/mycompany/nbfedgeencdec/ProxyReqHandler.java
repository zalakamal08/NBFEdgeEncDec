package com.mycompany.nbfedgeencdec;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.proxy.http.InterceptedRequest;
import burp.api.montoya.proxy.http.ProxyRequestHandler;
import burp.api.montoya.proxy.http.ProxyRequestReceivedAction;
import burp.api.montoya.proxy.http.ProxyRequestToBeSentAction;


public class ProxyReqHandler implements ProxyRequestHandler{
    public MontoyaApi api;
    public ProxyReqHandler(MontoyaApi api){
    this.api=api;
    }
    
    @Override
    public ProxyRequestReceivedAction handleRequestReceived(InterceptedRequest ir) {
        
        String body = ir.bodyToString();
        api.logging().logToOutput(body);
        String payload1 = JsonExtractor.extractValue(body,"test1");
        api.logging().logToOutput(payload1);
        HttpRequest request = ir.withHeader("htest1",payload1);
        return  ProxyRequestReceivedAction.continueWith(request) ;
    }

    @Override
    public ProxyRequestToBeSentAction handleRequestToBeSent(InterceptedRequest ir) {
        HttpRequest request = ir.withHeader("test", "value2");
        return ProxyRequestToBeSentAction.continueWith(request);
    }

    
}
