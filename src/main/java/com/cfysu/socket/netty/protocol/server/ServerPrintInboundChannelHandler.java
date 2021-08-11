package com.cfysu.socket.netty.protocol.server;

import com.alibaba.fastjson.JSON;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author canglong
 * @Date 2021/6/4
 */
public class ServerPrintInboundChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(Thread.currentThread().getName() + ": channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(Thread.currentThread().getName() + ": server read:" + JSON.toJSONString(msg));

        //ctx.write(msg);
        ctx.channel().write(msg);
        //ctx.channel().pipeline().write(msg);
        //往下一个节点传递
        //super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
