package com.cfysu.webservice;


import com.cfysu.webservice.service.HelloService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class HelloClient {
    public static void main(String[] args){
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(HelloService.class);
        jaxWsProxyFactoryBean.setAddress("http://localhost:9090/hello");
        HelloService helloService = (HelloService) jaxWsProxyFactoryBean.create();
        String res = helloService.sayHello("cj");
        System.out.println("WebService res:" + res);
    }
}
