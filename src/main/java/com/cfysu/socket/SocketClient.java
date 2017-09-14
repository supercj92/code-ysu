package com.cfysu.socket;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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

    public SocketClient(PrintWriter printWriter){
        this.printWriter = printWriter;
    }

    public static void main(String[] args){
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            //启动线程发送消息
            new Thread(new FutureTask<String>(new SocketClient(writer))).start();

            //用户发送消息
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
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
    public String call() throws Exception {
        int msgCount = 0;
        while (true){
            Thread.sleep(10000);
            msgCount++;
            printWriter.println("---thread msg---" + msgCount);
            printWriter.flush();
            LOGGER.info("---thread msg 已发出---" + msgCount);
        }
    }
}
