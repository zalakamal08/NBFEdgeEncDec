package com.mycompany.nbfedgeencdec;

import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.proxy.http.InterceptedResponse;
import burp.api.montoya.proxy.http.ProxyResponseHandler;
import burp.api.montoya.proxy.http.ProxyResponseReceivedAction;
import burp.api.montoya.proxy.http.ProxyResponseToBeSentAction;


public class ProxyRespHandler implements ProxyResponseHandler{

    @Override
    public ProxyResponseReceivedAction handleResponseReceived(InterceptedResponse interceptedResponse) {
        HttpResponse response = interceptedResponse.withAddedHeader("Test2", "TESST");
        return ProxyResponseReceivedAction.continueWith(response);
    }

    @Override
    public ProxyResponseToBeSentAction handleResponseToBeSent(InterceptedResponse interceptedResponse) {
       
        return ProxyResponseToBeSentAction.continueWith(interceptedResponse);
    }
    
}
