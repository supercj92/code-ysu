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
        final ServerSocket serverSocket = new ServerSocket(8888);
        LOGGER.info("SocketServer:服务器端已启动,正在监听...");
        while (true){
            //一直监听
            //阻塞等待新connection
            final Socket socket = serverSocket.accept();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //启动新线程处理客户端请求
            new Thread(new Runnable() {
                @Override
                public void run() {
                    LOGGER.info("启动新线程处理客户端请求,threadId:" + Thread.currentThread().getId());
                    //while (true){
                    for(int i = 0;i < 5;i++){

                        String clientMsg = null;
                        try {
                            clientMsg = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        LOGGER.info("reader:" + reader.hashCode() + ".SocketServer:服务器端接受到了消息===>>>" + clientMsg + ".threadId:" + Thread.currentThread().getId());
                    }
                    try {
                        reader.close();
                        socket.close();
                        serverSocket.close();
                        LOGGER.info("socket已关闭");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //}
                }
            }).start();

            //响应客户端
            //PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            //writer.write("a msg from server");
            //writer.flush();
            //关掉之后将无法继续通信
            //writer.close();
        }
    }
}
