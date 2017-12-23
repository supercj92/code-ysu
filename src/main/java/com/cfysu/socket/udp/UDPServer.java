package com.cfysu.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by cj on 17-12-23.
 */
public class UDPServer {
    public static void main(java.lang.String[] args) throws Exception {
        new UDPServer().startServer();
    }
    public void startServer() throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(7777);
        while (true){
            byte[] in = new byte[1024];
            DatagramPacket inPacket = new DatagramPacket(in, in.length);
            serverSocket.receive(inPacket);
            String clientMsg =  new String(in, 0, in.length, "utf-8");
            String serverMsg = "a msg from udp server:" + clientMsg;
            byte[] out = serverMsg.getBytes("utf-8");
            DatagramPacket outPacket = new DatagramPacket(out, out.length, inPacket.getSocketAddress());
            serverSocket.send(outPacket);
        }
    }
}
