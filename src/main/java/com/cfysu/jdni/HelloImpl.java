package com.cfysu.jdni;

/**
 * @Author canglong
 * @Date 2022/1/6
 */
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello {
    public HelloImpl() throws java.rmi.RemoteException {
        super();
    }

    @Override
    public String sayHello(String from) throws java.rmi.RemoteException {
        System.out.println("Hello from " + from + "!!");
        return "sayHello";
    }
}
