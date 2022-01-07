package com.cfysu.jdni;

/**
 * @Author canglong
 * @Date 2022/1/6
 */
public interface Hello extends java.rmi.Remote {
    public String sayHello(String from) throws java.rmi.RemoteException;
}
