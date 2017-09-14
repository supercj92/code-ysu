package com.cfysu.socket;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by cj on 17-6-25.
 */
public class SocketServer {

    private static final Logger LOGGER = Logger.getLogger(SocketClient.class);
    public static void main(String[] args){
        SocketServer socketServer = new SocketServer();
        try {
            socketServer.startServer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        LOGGER.info("SocketServer:服务器端已启动,正在监听...");
        //阻塞等待联接
        Socket socket = serverSocket.accept();
        while (true){
            //一直监听
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientMsg = reader.readLine();
            LOGGER.info("SocketServer:服务器端接受到了消息===>>>" + clientMsg);

            //响应客户端
            //PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            //writer.write("a msg from server");
            //writer.flush();
            //关掉之后将无法继续通信
            //writer.close();
        }
    }
}
