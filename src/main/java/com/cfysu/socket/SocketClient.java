package com.cfysu.socket;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by cj on 17-6-25.
 */
public class SocketClient implements Callable{
    private static final Logger LOGGER = Logger.getLogger(SocketClient.class);

    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    public SocketClient(PrintWriter printWriter, BufferedReader bufferedReader){
        this.printWriter = printWriter;
        this.bufferedReader = bufferedReader;
    }

    public static void main(String[] args){
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //启动线程接收消息
            new Thread(new FutureTask<String>(new SocketClient(writer, reader))).start();
            //主线程，用户发送消息
            Scanner scanner = new Scanner(System.in);
            while (true){
                String userMsg = scanner.next();
                if("bye".equals(userMsg)){
                    LOGGER.info("---用户退出---");
                    return;
                }
                writer.println(userMsg);
                writer.flush();
                LOGGER.info("用户信息已发出");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String call() {
        int msgCount = 0;
        while (true){
            //Thread.sleep(10000);
            //msgCount++;
            //printWriter.println("---thread msg---" + msgCount);
            //printWriter.flush();
            LOGGER.info("等待服务器回复消息");
            String receiveMsg = null;
            try {
                receiveMsg = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(null == receiveMsg){
                LOGGER.info("客户端收消息线程退出");
                return "exit";
            }
            LOGGER.info("---收到新消息---:" + receiveMsg);
        }
    }
}
