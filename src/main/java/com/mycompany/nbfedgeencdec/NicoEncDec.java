package com.mycompany.nbfedgeencdec;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class NicoEncDec implements BurpExtension  {

    public static String KEY ;
    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("Nico EncDec");
        ProxyReqHandler preqhandler = new ProxyReqHandler(api);
        api.proxy().registerRequestHandler(preqhandler);
        
//        ProxyRespHandler presphandler = new ProxyRespHandler();
//        api.proxy().registerResponseHandler(presphandler);


        NicoEncDecUI UI = new NicoEncDecUI(api);
        api.userInterface().registerSuiteTab("NICO Encryption", UI.getPanel()); 

    }
}
