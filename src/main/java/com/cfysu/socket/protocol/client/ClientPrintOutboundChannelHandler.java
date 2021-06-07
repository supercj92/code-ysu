package com.cfysu.socket.protocol.client;

import com.alibaba.fastjson.JSON;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Author canglong
 * @Date 2021/6/4
 */
public class ClientPrintOutboundChannelHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("client write:" + JSON.toJSONString(msg));
        ctx.write(msg);
    }
}
