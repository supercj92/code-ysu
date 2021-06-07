package com.cfysu.socket.protocol.codec;

import com.alibaba.fastjson.JSON;

import com.cfysu.io.SerialUtil;
import com.cfysu.socket.protocol.Protocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author canglong
 * @Date 2021/6/4
 */
public class ProtocolEncoder extends MessageToByteEncoder<Protocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Protocol msg, ByteBuf out) throws Exception {
        System.out.println("encode");
        byte[] protocolByte = SerialUtil.serializeByJdk(msg);
        out.writeBytes(protocolByte);
    }
}
