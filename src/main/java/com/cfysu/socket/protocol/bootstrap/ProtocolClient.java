package com.cfysu.socket.protocol.bootstrap;

import com.cfysu.socket.protocol.client.ClientInboundChannelHandler;
import com.cfysu.socket.protocol.client.ClientPrintOutboundChannelHandler;
import com.cfysu.socket.protocol.Protocol;
import com.cfysu.socket.protocol.codec.ProtocolEncoder;
import com.cfysu.socket.protocol.codec.ProtocolDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author canglong
 * @Date 2021/6/4
 */
public class ProtocolClient {
    public static void main(String[] args) {
        //Protocol protocol = new Protocol();
        //protocol.setType(Protocol.TYPE_IN);
        //protocol.setPayload(1);
        //System.out.println(SerialUtil.serializeByJdk(protocol).length);

        new ProtocolClient().start();
    }

    public void start(){
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();

        try {
            bootstrap.group(nioEventLoopGroup).
                channel(NioSocketChannel.class).
                handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                        //out
                        ch.pipeline().addLast(new ProtocolEncoder());
                        ch.pipeline().addLast(new ClientPrintOutboundChannelHandler());

                        //in
                        ch.pipeline().addLast(new ProtocolDecoder());
                        ch.pipeline().addLast(new ClientInboundChannelHandler());
                    }
                });

            ChannelFuture channelFuture = bootstrap.connect("localhost", 9999).sync();
            System.out.println("client start..., begin send msg");
            sendMsg(channelFuture.channel());
            channelFuture.channel().closeFuture().sync();
            System.out.println("client stop 1");
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("client stop 2");
            nioEventLoopGroup.shutdownGracefully();
        }
    }

    private void sendMsg(Channel channel){
        int payload = 1;
        while (true) {
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                break;
            }
            Protocol protocol = new Protocol();
            protocol.setType(Protocol.TYPE_IN);
            protocol.setPayload(payload++);

            channel.writeAndFlush(protocol);
        }
    }
}
