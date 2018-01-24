package com.cfysu.webservice.cxf.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloService {
    String sayHello(@WebParam(name = "name") String name);
}
