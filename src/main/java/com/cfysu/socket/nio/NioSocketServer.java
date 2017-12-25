package com.cfysu.socket.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioSocketServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NioSocketServer.class);

    private Charset charset = Charset.forName("utf-8");

    public static void main(String[] args) throws Exception{
        new NioSocketServer().startNioServer();
    }

    public void startNioServer() throws IOException {
        //创建ServerSocketChannel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("127.0.0.1", 9999));
        //设置为非阻塞模式
        ssc.configureBlocking(false);
        //为ssc注册选择器
        Selector selector=Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ExecutorService pool = Executors.newFixedThreadPool(4);

        int counter = 0;
        LOGGER.info(Thread.currentThread().getName() + ":服务器端已经启动，正在监听。。。");
        int count = 0;
        //创建处理器
        while((count = selector.select()) > 0){
            LOGGER.info(Thread.currentThread().getName() + ":开始处理请求;数量==>>" + count +";selector==>>" + ++counter);
            // 获取待处理的SelectionKey
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();

            while(keyIter.hasNext()){
                SelectionKey key = keyIter.next();
                // 启动新线程处理SelectionKey
                //new Thread(new ChatHandler(key)).start();
                //pool.submit(new ChatHandler(key));
                if(key.isAcceptable()){
                    handleAccept(key);
                }

                if(key.isReadable()){
                    handleRead(key);
                }
                keyIter.remove();
//                new Thread(new ChatHandler(key)).start();
//                keyIter.remove();
            }
        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        LOGGER.info(Thread.currentThread().getName() + ":处理isAcceptable");
        SocketChannel clientChannel=((ServerSocketChannel)key.channel()).accept();
        //SocketChannel clientChannel = ssc.accept();
        if(clientChannel == null){
            LOGGER.info(Thread.currentThread().getName() + ":clientChannel is null");
            return;
        }
        clientChannel.configureBlocking(false);
        clientChannel.register(key.selector(), SelectionKey.OP_READ);
        key.interestOps(SelectionKey.OP_ACCEPT);
        LOGGER.info(Thread.currentThread().getName() + ":接受客户端连接");
    }

    private void handleRead(SelectionKey key) throws IOException {
        LOGGER.info(Thread.currentThread().getName() + ":处理isReadable");
        // 获取channel
        SocketChannel sc = (SocketChannel) key.channel();
        // 获取buffer并重置
        //ByteBuffer buffer=(ByteBuffer)key.attachment();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        // 没有读到内容则关闭
        if (sc.read(buffer) == -1) {
            sc.close();
        } else {
            //准备读取
            buffer.flip();
            String clientMsg = charset.decode(buffer).toString();
            String serverMsg = "a msg from nio server:" + clientMsg;
            //buffer.clear();
            //buffer.flip();
            sc.write(charset.encode(serverMsg));
            LOGGER.info(Thread.currentThread().getName() + ":响应请求==>>" + serverMsg + "" + buffer);
            key.interestOps(SelectionKey.OP_READ);
            //sc.close();
        }
    }
    private class ChatHandler implements Runnable{

        private SelectionKey key;

        private ChatHandler(SelectionKey key){
            this.key = key;
        }

        private void handleAccept() throws IOException {
            LOGGER.info(Thread.currentThread().getName() + ":处理isAcceptable");
            SocketChannel clientChannel=((ServerSocketChannel)key.channel()).accept();
            //SocketChannel clientChannel = ssc.accept();
            if(clientChannel == null){
                LOGGER.info(Thread.currentThread().getName() + ":clientChannel is null");
                return;
            }
            clientChannel.configureBlocking(false);
            clientChannel.register(key.selector(), SelectionKey.OP_READ);
            key.interestOps(SelectionKey.OP_ACCEPT);
            LOGGER.info(Thread.currentThread().getName() + ":接受客户端连接");
        }

        private void handleRead() throws IOException {
            LOGGER.info(Thread.currentThread().getName() + ":处理isReadable");
            // 获取channel
            SocketChannel sc=(SocketChannel)key.channel();
            // 获取buffer并重置
            //ByteBuffer buffer=(ByteBuffer)key.attachment();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.clear();
            // 没有读到内容则关闭
            if(sc.read(buffer)==-1){
                sc.close();
            } else {
                //准备读取
                buffer.flip();
                String clientMsg = charset.decode(buffer).toString();
                String serverMsg = "a msg from nio server:" + clientMsg;
                //buffer.clear();
                //buffer.flip();
                sc.write(charset.encode(serverMsg));
                LOGGER.info(Thread.currentThread().getName() + ":响应请求==>>" + serverMsg + "" + buffer);
                key.interestOps(SelectionKey.OP_READ);
                //sc.close();
            }
        }

        @Override
        public void run() {
            try{
                if(key.isAcceptable()){
                    handleAccept();
                }

                if(key.isReadable()){
                    handleRead();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            LOGGER.info(Thread.currentThread().getName() + ":线程结束");
        }
    }

    private static class HttpHandler implements Runnable{
        private int bufferSize = 1024;
        private String  localCharset = "UTF-8";
        private SelectionKey key;

        public HttpHandler(SelectionKey key){
            this.key = key;
        }

        public void handleAccept() throws IOException {
            SocketChannel clientChannel=((ServerSocketChannel)key.channel()).accept();
            clientChannel.configureBlocking(false);
            clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
        }

        public void handleRead() throws IOException {
            // 获取channel
            SocketChannel sc=(SocketChannel)key.channel();
            // 获取buffer并重置
            ByteBuffer buffer=(ByteBuffer)key.attachment();
            buffer.clear();
            // 没有读到内容则关闭
            if(sc.read(buffer)==-1){
                sc.close();
            } else {
                // 接收请求数据
                buffer.flip();
                String receivedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();

                // 控制台打印请求报文头
                String[] requestMessage = receivedString.split("\r\n");
                for(String s: requestMessage){
                    System.out.println(s);
                    // 遇到空行说明报文头已经打印完
                    if(s.isEmpty())
                        break;
                }

                // 控制台打印首行信息
                String[] firstLine = requestMessage[0].split(" ");
                System.out.println();
                System.out.println("Method:\t"+firstLine[0]);
                System.out.println("url:\t"+firstLine[1]);
                System.out.println("HTTP Version:\t"+firstLine[2]);
                System.out.println();

                // 返回客户端
                StringBuilder sendString = new StringBuilder();
                sendString.append("HTTP/1.1 200 OK\r\n");//响应报文首行，200表示处理成功
                sendString.append("Content-Type:text/html;charset=" + localCharset+"\r\n");
                sendString.append("\r\n");// 报文头结束后加一个空行

                sendString.append("<html><head><title>显示报文</title></head><body>");
                sendString.append("接收到请求报文是：<br/>");
                for(String s: requestMessage){
                    sendString.append(s + "<br/>");
                }
                sendString.append("</body></html>");
                buffer = ByteBuffer.wrap(sendString.toString().getBytes(localCharset));
                sc.write(buffer);
                sc.close();
            }
        }

        @Override
        public void run() {
            try{
                // 接收到连接请求时
                if(key.isAcceptable()){
                    handleAccept();
                }
                // 读数据
                if(key.isReadable()){
                    handleRead();
                }
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
