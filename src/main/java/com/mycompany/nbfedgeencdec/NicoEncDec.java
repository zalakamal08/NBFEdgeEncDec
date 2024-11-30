package com.mycompany.nbfedgeencdec;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import java.util.Arrays;
import java.util.List;

public class NicoEncDec implements BurpExtension  {

    public static String KEY ;
    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("Nico EncDec");
        
        List<String> keys = Arrays.asList("appzillonHeader","appzillonBody","appzillonSafe","appzillonMessages","appzillonErrors");
        
        
        ProxyReqHandler preqhandler = new ProxyReqHandler(api,keys.subList(0, 2));
        api.proxy().registerRequestHandler(preqhandler);
        
        ProxyRespHandler presphandler = new ProxyRespHandler(api,keys);        
        api.proxy().registerResponseHandler(presphandler);
        
        NicoEncDecUI UI = new NicoEncDecUI(api);
        api.userInterface().registerSuiteTab("NICO Encryption", UI.getPanel()); 

    }
}
