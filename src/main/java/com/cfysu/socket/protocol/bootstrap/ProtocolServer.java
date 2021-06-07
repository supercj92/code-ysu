package com.cfysu.socket.protocol.bootstrap;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import com.cfysu.socket.protocol.codec.ProtocolEncoder;
import com.cfysu.socket.protocol.server.ServerPrintInboundChannelHandler;
import com.cfysu.socket.protocol.server.ServerResponseOutboundChannelHandler;
import com.cfysu.socket.protocol.codec.ProtocolDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author canglong
 * @Date 2021/6/4
 */
@Slf4j
public class ProtocolServer {

    public static void main(String[] args) {
        new ProtocolServer().start();
    }

    private void start(){
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(5, new NamedThreadFactory("netty-boss"));
        NioEventLoopGroup workerGroup  = new NioEventLoopGroup(5, new NamedThreadFactory("netty-worker"));
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).
                channel(NioServerSocketChannel.class).
                childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //in
                        ch.pipeline().addLast(new ProtocolDecoder());
                        ch.pipeline().addLast(new ServerPrintInboundChannelHandler());

                        //out
                        ch.pipeline().addLast(new ProtocolEncoder());
                        ch.pipeline().addLast(new ServerResponseOutboundChannelHandler());
                    }
                });
            ChannelFuture sync = serverBootstrap.bind(9999).sync();
            System.out.println("server start...");
            sync.channel().closeFuture().sync();
            System.out.println("server stop 1");
        }catch (Exception e){
            log.error("start error", e);
        } finally {
            System.out.println("server stop 2");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public class NamedThreadFactory implements ThreadFactory {

        private final String prefix;

        private final AtomicInteger thradCount = new AtomicInteger(1);

        NamedThreadFactory(String prefix){
            this.prefix = prefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, prefix + "-" + thradCount.getAndIncrement());
        }
    }
}
