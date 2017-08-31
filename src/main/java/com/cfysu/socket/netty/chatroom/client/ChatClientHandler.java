package com.cfysu.socket.netty.chatroom.client;

import com.cfysu.socket.netty.chatroom.message.ChatMessageUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: wangdong
 * Date: 14-8-14
 * To change this template use File | Settings | File Templates.
 */
public class ChatClientHandler extends ChannelInboundHandlerAdapter {
	private static Logger _logger = Logger.getLogger(ChatClientHandler.class);
    private ChatClient chatClient;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String clientId = "{" + UUID.randomUUID() + "}";
        chatClient = new ChatClient(clientId, ctx);

        new Thread(chatClient, "ChatClient").start();

        new Thread(new Heartbeat(), "Heartbeat").start();

        chatClient.send(ChatMessageUtils.generateRegisterMessage(clientId));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String body = (String) msg;
		System.out.println("Client Received msg : " + body);
		_logger.info("Client Received msg : " + body);
        chatClient.setText(body);
    }

    /**
    *
    * @param ctx
    * @param cause
    *
    **/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        _logger.error("exceptionCaught",cause);
        ctx.close();
    }

    class Heartbeat implements Runnable {
        /**
         * 每秒钟向服务器端发送心跳
         */
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    chatClient.send(ChatMessageUtils.generateHeartbeatMessage(chatClient.getUserName()));
                } catch (Exception e) {
                    _logger.error("Heartbeat send is err.",e);
                }
            }
        }
    }
}
