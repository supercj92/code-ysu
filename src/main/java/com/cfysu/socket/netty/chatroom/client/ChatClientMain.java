package com.cfysu.socket.netty.chatroom.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
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
public class ChatClientMain {
    private static Logger _logger = Logger.getLogger(ChatClientMain.class);
    public void connect(int port, String host) throws Exception {
        //客户端netty nio
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap boot = new Bootstrap();
            boot.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new LineBasedFrameDecoder(1024))
                                    .addLast(new StringDecoder(Charset.forName("UTF-8")))
                                    .addLast(new ChatClientHandler());
                        }
                    });

            ChannelFuture f = boot.connect(host, port).sync();

            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
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
        new ChatClientMain().connect(port, "127.0.0.1");
    }
}
