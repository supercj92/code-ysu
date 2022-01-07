package com.cfysu.jdni.log4j;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.naming.NamingException;
import javax.naming.Reference;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import com.sun.jndi.rmi.registry.RemoteReference;

/**
 * @Author canglong
 * @Date 2022/1/6
 */
public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            RemoteReference hello = (RemoteReference) registry.lookup("exp");
            Reference reference = hello.getReference();
            System.out.println("=========");
        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
