package com.cfysu.jdni;

/**
 * @Author canglong
 * @Date 2022/1/6
 */
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class HelloClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        System.setProperty(Context.PROVIDER_URL, "rmi://localhost:1099");
        InitialContext context = new InitialContext();
        Hello rmiObject = (Hello) context.lookup("java:hello");
        System.out.println(rmiObject.sayHello("world"));
        context.close();
    }
}
