package com.cfysu.socket.protocol.codec;

import java.util.List;

import com.cfysu.io.SerialUtil;
import com.cfysu.socket.protocol.Protocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import static com.cfysu.socket.protocol.Protocol.TYPE_IN;

/**
 * @Author canglong
 * @Date 2021/6/4
 * 解码器
 */
public class ProtocolDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() > 117){
            System.out.println("decode---");
            ByteBuf byteBuf = in.readBytes(117);
            Protocol protocol = SerialUtil.deserializeByJdk(byteBuf.array());
            out.add(protocol);
        }
    }
}
