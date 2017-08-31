package com.cfysu.socket.netty.chatroom.server;

import com.cfysu.socket.netty.chatroom.message.ChatMessage;
import com.cfysu.socket.netty.chatroom.message.ChatMessageUtils;
import com.cfysu.socket.netty.chatroom.session.ChatSession;
import com.cfysu.socket.netty.chatroom.session.ChatSessionsManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: wangdong
 * Date: 14-8-18
 * To change this template use File | Settings | File Templates.
 */
public class ChatServerHandler extends ChannelInboundHandlerAdapter {
	private static Logger _logger = Logger.getLogger(ChatServerHandler.class);
    private ChatSessionsManager sessionsManager;

    public ChatServerHandler(ChatSessionsManager sessionsManager) {
        this.sessionsManager = sessionsManager;
    }

    /**
    *
    * @param ctx
    * @param msg
    *
    **/
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String body = (String) msg;
		if(StringUtils.isEmpty(body)){
			return;
		}
		_logger.info("Service Received msg : " + body);

        handler(ctx, body);
    }

    private void handler(ChannelHandlerContext ctx, String body) {
		if(StringUtils.isEmpty(body)){
			return;
		}
		ChatMessage message = ChatMessageUtils.generateMessage(body);


        if (ChatMessageUtils.isRegister(message)) {
            sessionsManager.registeredSession(message.getFrom(), ctx);
            sessionsManager.broadcast("欢迎" + message.getFrom() + "加入聊天室~!");
        }else if (ChatMessageUtils.isHeartBeat(message)) {

			_logger.info(message.getFrom() + " heartbeat~!");

            ChatSession session = sessionsManager.getSession(message.getFrom());
            if(session != null) {
                session.access();
            }
			//sessionsManager.broadcast("welcome " + message.getFrom() + " come in!");
        }else if (ChatMessageUtils.isTo(message)) {

            System.out.println(message.getFrom() + "私聊:" + message.getTo() + " ˵: " + message.getBody());

            sessionsManager.send(message.getTo(), message.getFrom() + " 私聊˵ : " + message.getBody());
        }else if (ChatMessageUtils.isUnRegister(message)) {
            sessionsManager.unregisteredSession(message.getFrom());
            sessionsManager.broadcast(message.getFrom() + "离开了聊天室~!");
        }else if(ChatMessageUtils.isBroadcast(message)) {
            System.out.println(message.getFrom() + " 群聊˵ : " + message.getBody());

            sessionsManager.broadcast(message.getFrom() + " 群聊˵ : " + message.getBody());
        }else if (ChatMessageUtils.isGetAll(message)) {

            System.out.println(message.getFrom() + " 获取所有在线的人");

            sessionsManager.send(message.getFrom(), getOnlineSessions());
        }
    }

    private String getOnlineSessions() {
        StringBuilder builder = new StringBuilder();
        for(String sessionId : sessionsManager.getSessionKeys()) {
            builder.append(sessionId);
            builder.append("|");
        }
        return builder.toString();
    }
}
