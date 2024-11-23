package com.mycompany.nbfedgeencdec;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class NBFEdgeEncDec implements BurpExtension  {

    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("NBFEdge EncDec");
        ProxyReqHandler preqhandler = new ProxyReqHandler(api);
        api.proxy().registerRequestHandler(preqhandler);
        
//        ProxyRespHandler presphandler = new ProxyRespHandler();
//        api.proxy().registerResponseHandler(presphandler);

    }
}
