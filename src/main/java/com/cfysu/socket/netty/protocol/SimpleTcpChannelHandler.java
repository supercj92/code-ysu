package com.cfysu.socket.netty.protocol;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author canglong
 * @Date 2021/6/4
 */
public class SimpleTcpChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;

        System.out.println(byteBuf.toString(Charset.forName("utf-8")));

        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes("xx".getBytes());

        ctx.channel().writeAndFlush(buffer);
        ctx.channel().close();
    }
}
