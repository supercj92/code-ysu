package com.cfysu.socket;

import org.apache.log4j.Logger;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by cj on 17-6-25.
 */
public class SocketClient {
    private static final Logger LOGGER = Logger.getLogger(SocketClient.class);

    public static void main(String[] args){
        try {
            Socket socket = new Socket("127.0.0.1", 8888);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter printWriter = new PrintWriter(outputStreamWriter, true);
            printWriter.println("hello world");
            LOGGER.info("SocketClient:客户端已经发送消息");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
