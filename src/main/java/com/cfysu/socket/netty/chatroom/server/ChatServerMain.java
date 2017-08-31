package com.cfysu.socket.netty.chatroom.server;

import com.cfysu.socket.netty.chatroom.session.ChatSessionsManager;
import com.cfysu.socket.netty.chatroom.client.ChatClient;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.apache.log4j.Logger;

import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: wangdong
 * Date: 14-8-18
 * To change this template use File | Settings | File Templates.
 */
public class ChatServerMain {
    private static Logger _logger = Logger.getLogger(ChatClient.class);
    private ChatSessionsManager sessionsManager = new ChatSessionsManager();

    private ChatServerMain() {
        ChatHeartBeatCheckTimer heartBeatTimer = new ChatHeartBeatCheckTimer(sessionsManager);
        Thread checkDestinationsThread = new Thread(heartBeatTimer, "CheckSessionsThread");
        checkDestinationsThread.setDaemon(true);
        checkDestinationsThread.start();

        sessionsManager.setChatSessionListener(heartBeatTimer);
    }

    /**
    *
    * @param port
    *
    **/
    public void bind(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //服务器端netty nio
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new LineBasedFrameDecoder(1024))
                                    .addLast(new StringDecoder(Charset.forName("UTF-8")))
                                    .addLast(new ChatServerHandler(sessionsManager));
                        }
                    });
            ChannelFuture f = b.bind(port).sync();

            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
    *
    * @param args
    *
    **/
    public static void main(String[] args) throws Exception {
        int port = 8000;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                _logger.error("occur err.",e);
            }
        }

        new ChatServerMain().bind(port);
    }
}
