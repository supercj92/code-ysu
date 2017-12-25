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
public class SocketClient {

    private static final Logger LOGGER = Logger.getLogger(SocketClient.class);

    public static void main(String[] args){
        try {
            new SocketClient().startClient();
        } catch (IOException e) {
            LOGGER.error("IO异常", e);
        }
    }

    public void startClient() throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //启动线程接收消息
        new Thread(new FutureTask<String>(new Receiver(reader))).start();
        //主线程，用户发送消息
        Scanner scanner = new Scanner(System.in);
        while (true){
            String userMsg = scanner.next();
            if("bye".equals(userMsg)){
                LOGGER.info(Thread.currentThread().getName() + ":bye bye");
                break;
            }
            writer.println(userMsg);
            writer.flush();
            LOGGER.info(Thread.currentThread().getName() + ":用户信息已发出");
        }
        LOGGER.info(Thread.currentThread().getName() + ":主线程退出");
    }

    private class Receiver implements Callable<String>{

        private BufferedReader reader;

        public Receiver(BufferedReader reader){
            this.reader = reader;
        }

        @Override
        public String call() {
            int msgCount = 0;
            while (true){
                //Thread.sleep(10000);
                //msgCount++;
                //printWriter.println("---thread msg---" + msgCount);
                //printWriter.flush();
                LOGGER.info(Thread.currentThread().getName() + ":等待服务器回复消息");
                String receiveMsg = null;
                try {
                    receiveMsg = reader.readLine();
                } catch (IOException e) {
                    LOGGER.error("服务器端退出", e);
                }
                if(null == receiveMsg){
                    LOGGER.info(Thread.currentThread().getName() + ":客户端收消息线程退出");
                    return "exit";
                }
                LOGGER.info(Thread.currentThread().getName() + ":收到新消息===>>>" + receiveMsg);
            }
        }
    }
}
