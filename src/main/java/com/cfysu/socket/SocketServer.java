package com.cfysu.socket;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by cj on 17-6-25.
 */
public class SocketServer {

    private static final Logger LOGGER = Logger.getLogger(SocketClient.class);
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            LOGGER.info("SocketServer:服务器端已启动,正在监听...");

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String clientMsg = bufferedReader.readLine();
            LOGGER.info("SocketServer:服务器端接受到了消息===>>>" + clientMsg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
