package com.cfysu.webservice.cxf.service.impl;


import com.cfysu.webservice.cxf.service.HelloService;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "com.cfysu.webservice.cxf.service.HelloService", serviceName = "hello")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(@WebParam(name = "name") String name) {
        return "hello," + name;
    }
}
