package com.cfysu.socket.nio;

import com.cfysu.socket.SocketClient;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class NioSocketClient {
    private static final Logger LOGGER = Logger.getLogger(SocketClient.class);

    private Selector selector;
    private Charset charset = Charset.forName("utf-8");

    public static void main(String[] args){
        try {
            new NioSocketClient().startClient();
        } catch (IOException e) {
            LOGGER.error("IO异常", e);
        }
    }

    public void startClient() throws IOException {

        selector = Selector.open();
        SocketChannel socket = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        socket.configureBlocking(false);
        socket.register(selector, SelectionKey.OP_READ);

        //启动线程接收消息
        new Thread(new FutureTask<String>(new Receiver())).start();

        //主线程，用户发送消息
        Scanner scanner = new Scanner(System.in);
        while (true){
            LOGGER.info(Thread.currentThread().getName() + ":等待用户输入...");
            String userMsg = scanner.nextLine();
            if("bye".equals(userMsg)){
                LOGGER.info(Thread.currentThread().getName() + ":bye bye");
                break;
            }
            socket.write(charset.encode(userMsg));
            LOGGER.info(Thread.currentThread().getName() + ":用户信息已发出");
        }
        LOGGER.info(Thread.currentThread().getName() + ":主线程退出");
    }

    private class Receiver implements Callable<String> {

        @Override
        public String call() throws IOException {
            while (selector.select() > 0){
                LOGGER.info(Thread.currentThread().getName() + ":收到新消息，开始处理");

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isReadable()){
                        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                        socketChannel.configureBlocking(false);
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        byteBuffer.clear();
                        StringBuilder stringBuilder = new StringBuilder();
                        while (socketChannel.read(byteBuffer) > 0){
                            byteBuffer.flip();
                            stringBuilder.append(charset.decode(byteBuffer));
                            //byteBuffer.clear();
                        }
                        LOGGER.info(Thread.currentThread().getName() + ":收到新消息===>>>" + stringBuilder.toString());
                        selectionKey.interestOps(SelectionKey.OP_READ);
                    }
                    //删除正在处理的key
                    iterator.remove();
                }
            }
            LOGGER.info(Thread.currentThread().getName() + ":线程结束");
            return null;
        }
    }
}
