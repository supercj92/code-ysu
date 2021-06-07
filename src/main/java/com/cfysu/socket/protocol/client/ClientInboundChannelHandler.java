package com.cfysu.socket.protocol.client;

import com.alibaba.fastjson.JSON;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author canglong
 * @Date 2021/6/4
 */
public class ClientInboundChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client read:" + JSON.toJSONString(msg));
    }
}
