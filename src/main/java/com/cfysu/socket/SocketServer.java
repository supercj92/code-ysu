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
            final PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            //final PrintStream printStream = new PrintStream(socket.getOutputStream());

            //启动新线程处理客户端请求
            new Thread(new Runnable() {
                @Override
                public void run() {
                    LOGGER.info("启动新线程处理客户端请求,threadId:" + Thread.currentThread().getId());
                    while (true){
                    //for(int i = 0;i < 5;i++){

                        String clientMsg = null;
                        try {
                            clientMsg = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(null == clientMsg){
                            LOGGER.info("服务器端收消息线程退出.threadid:" + Thread.currentThread().getId());
                            return;
                        }
                        LOGGER.info("reader:" + reader.hashCode() + ".SocketServer:服务器端接受到了消息===>>>" + clientMsg + ".threadId:" + Thread.currentThread().getId());
                    //}
                        //响应客户端
                        writer.write("a msg from server:" + clientMsg);
                        //如果不加换行符，则readline()一直阻塞
                        writer.write(System.getProperty("line.separator"));
                        //writer.println("a msg from server:" + clientMsg);
                        writer.flush();
                        LOGGER.info("已回复客户端消息");
                    }
                }
            }).start();
        }
    }
}
