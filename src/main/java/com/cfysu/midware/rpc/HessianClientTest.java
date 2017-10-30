package com.cfysu.midware.rpc;

import com.caucho.hessian.client.HessianProxyFactory;
import com.test.hessian.service.HelloService;
import org.junit.Test;

import java.net.MalformedURLException;

/*
 * Created by longcangjian on 2017/6/15.
 *
  */


public class HessianClientTest {

    @Test
    public void testHessianClient(){
        String url = "http://localhost:8080/hessian";
        HessianProxyFactory factory = new HessianProxyFactory();
        HelloService helloService = null;
        try {
            helloService = (HelloService) factory.create(HelloService.class, url);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String res = helloService.sayHello("test msg");
        System.out.println("res:" + res);
    }
}
