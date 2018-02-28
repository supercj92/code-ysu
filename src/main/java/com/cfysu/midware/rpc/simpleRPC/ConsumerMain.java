package com.cfysu.midware.rpc.simpleRPC;

import java.net.InetSocketAddress;

public class ConsumerMain {
    public static void main(String[] args){
        RPCImporter<EchoService> importer = new RPCImporter<>();
        EchoService echoService = importer.importService(EchoServiceImpl.class, new InetSocketAddress("localhost", 8010));
        String res = echoService.echo("haha");
        System.out.println("res:" + res);
    }
}
