package com.cfysu.socket;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cj on 17-6-25.
 */
public class SocketServer {

    private static final Logger LOGGER = Logger.getLogger(SocketClient.class);

    public static void main(String[] args){
        try {
            new SocketServer().startServer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startServer() throws IOException {
        final ServerSocket serverSocket = new ServerSocket(8888);
        LOGGER.info(Thread.currentThread().getName() + ":服务器端已启动,正在监听...");
        ExecutorService pool = Executors.newFixedThreadPool(2);
        while (true){
            //一直监听
            //阻塞等待新connection
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            //final PrintStream printStream = new PrintStream(socket.getOutputStream());

            //启动新线程处理客户端请求
            pool.submit(new HttpWorker(reader, writer));
        }
    }

    private class Worker implements Runnable{

        private PrintWriter writer;
        private BufferedReader reader;

        public Worker(BufferedReader reader, PrintWriter writer){
            this.writer = writer;
            this.reader = reader;
        }

        @Override
        public void run() {
            LOGGER.info("启动新线程处理客户端请求,threadId:" + Thread.currentThread().getName());
            while (true){
                //for(int i = 0;i < 5;i++){

                String clientMsg = null;
                try {
                    clientMsg = reader.readLine();
                } catch (IOException e) {
                    LOGGER.error("客户端退出", e);
                }
                if(null == clientMsg){
                    LOGGER.info(Thread.currentThread().getName() + ":服务器端收消息线程退出");
                    return;
                }
                LOGGER.info(Thread.currentThread().getName() + ":服务器端接受到了消息===>>>" + clientMsg);
                //}
                //响应客户端
                writer.write("a msg from server:" + clientMsg);
                //如果不加换行符，则readline()一直阻塞
                writer.write(System.getProperty("line.separator"));
                //writer.println("a msg from server:" + clientMsg);
                writer.flush();

                LOGGER.info(Thread.currentThread().getName() + ":已回复客户端消息");
            }
        }
    }

    private class HttpWorker implements Runnable{

        private PrintWriter writer;
        private BufferedReader reader;

        public HttpWorker(BufferedReader reader, PrintWriter writer){
            this.writer = writer;
            this.reader = reader;
        }

        @Override
        public void run() {
            char[] buffer = new char[1024];
            StringBuilder stringBuilder = new StringBuilder();
            //while (true){
                try {
                    //reader.read(buffer) != -1){
                    reader.read(buffer);
                    stringBuilder.append(buffer);
                    //}
                    LOGGER.info(Thread.currentThread().getName());

                    StringBuilder sendString = new StringBuilder();
                    sendString.append("HTTP/1.1 200 OK\r\n");//响应报文首行，200表示处理成功
                    sendString.append("Content-Type:text/html;charset=utf-8\r\n");
                    sendString.append("\r\n");// 报文头结束后加一个空行

                    writer.write(sendString.append("haha").toString());
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    LOGGER.error("io异常", e);
                }
            //}
        }
    }
}
