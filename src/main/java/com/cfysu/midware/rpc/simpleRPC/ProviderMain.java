package com.cfysu.midware.rpc.simpleRPC;

import java.io.IOException;

public class ProviderMain {
    public static void main(String[] args){
        try {
            RPCExporter.exportSevice("localhost", 8010);
            System.out.println("server started...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
