package com.cfysu.socket.protocol.server;

import com.alibaba.fastjson.JSON;

import com.cfysu.socket.protocol.Protocol;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Author canglong
 * @Date 2021/6/4
 */
public class ServerResponseOutboundChannelHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        Protocol request = (Protocol)msg;
        promise.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    future.cause().printStackTrace();
                    future.channel().close();
                }
            }
        });

        //int res = 2 / 0;

        Protocol response = new Protocol();
        response.setType(Protocol.TYPE_OUT);
        response.setPayload(request.getPayload());

        System.out.println(Thread.currentThread().getName() + ": server response: " + JSON.toJSONString(response));

        ctx.writeAndFlush(response);

        //会导致StackOverflowError
        //ctx.channel().writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //cause.printStackTrace();
    }
}
