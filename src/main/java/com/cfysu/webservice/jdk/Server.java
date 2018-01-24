package com.cfysu.webservice.jdk;

import com.cfysu.webservice.jdk.service.impl.BusinessImpl;

import javax.xml.ws.Endpoint;

public class Server {
    public static void main(String[] args){
        Endpoint.publish("http://localhost:9527/BusinessService", new BusinessImpl());
        System.out.println("Server started");
    }
}
