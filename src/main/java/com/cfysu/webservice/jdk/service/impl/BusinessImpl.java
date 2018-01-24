package com.cfysu.webservice.jdk.service.impl;

import com.cfysu.webservice.jdk.service.Business;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "Business", serviceName = "BusinessService", targetNamespace = "http://jdk.wbservice.cfysu.com/client")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class BusinessImpl implements Business{
    @Override
    public String echo(String message) {
        return "hello," + message;
    }
}
