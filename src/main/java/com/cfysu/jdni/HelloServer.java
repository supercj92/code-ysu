package com.cfysu.jdni;

/**
 * @Author canglong
 * @Date 2022/1/6
 */
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class HelloServer {
    public static void main(String[] args) throws RemoteException, NamingException {
        LocateRegistry.createRegistry(1099);
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        System.setProperty(Context.PROVIDER_URL, "rmi://localhost:1099");
        InitialContext context = new InitialContext();
        context.bind("java:hello", new HelloImpl());
        context.close();
    }
}
