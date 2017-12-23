package com.cfysu.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Created by cj on 17-12-23.
 */
public class UDPClient {
    public static void main(String[] args) throws Exception {
        new UDPClient().startClient();
    }
    public void startClient() throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String str = scanner.nextLine();
            byte[] outByte = str.getBytes("utf-8");
            DatagramPacket outPacket = new DatagramPacket(outByte, outByte.length, InetAddress.getByName("127.0.0.1"), 7777);
            clientSocket.send(outPacket);
            DatagramPacket inPacket = new DatagramPacket(new byte[1024], 1024);
            clientSocket.receive(inPacket);
            byte[] bytes = inPacket.getData();
            System.out.println(new String(bytes, 0, bytes.length, "utf-8"));
        }
    }
}
