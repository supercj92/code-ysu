package com.cfysu.midware.rpc.simpleRPC;

public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String ping) {
        return "server receive a msg ==>>" + ping;
    }
}
