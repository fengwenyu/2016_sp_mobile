package com.shangpin.wireless.api.pay.bestpay.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "DealProcessorService", targetNamespace = "http://websvc/")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface DealProcessorService {


    @WebMethod(operationName = "dispatchCommand", action = "")
    @WebResult(name = "return", targetNamespace = "")
    public String dispatchCommand(
        @WebParam(name = "arg0", targetNamespace = "http://websvc/")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "http://websvc/")
        String arg1);

}
