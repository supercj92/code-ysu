package com.cfysu.socket.netty.chatroom.session;

import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: wangdong
 * Date: 14-8-18
 * To change this template use File | Settings | File Templates.
 */
public class ChatSessionsManager {
	private static Logger _logger = Logger.getLogger(ChatSessionsManager.class);

    private Map<String, ChatSession> sessions = new ConcurrentHashMap<String, ChatSession>();

    private ChatSessionListener chatSessionListener;

    public void registeredSession(String client, ChannelHandlerContext ctx) {

		sessions.put(client, generateSession(client, ctx));
		_logger.info(client + " register to Chart Server!");
	}

    /**
    *
    * @param client
    *
    **/
    public void unregisteredSession(String client) {
        sessions.remove(client);
		_logger.info(client + " unregister from Chart Server!");
    }

    /**
    *
    * @param message
    *
    **/
    public void broadcast(String message) {
        for (ChatSession ctx : sessions.values()) {
            ctx.send(message);
        }
    }

    /**
    *
    * @param client
    * @param message
    *
    **/
    public void send(String client, String message) {
        ChatSession ctx = sessions.get(client);
        if (ctx != null) {
            ctx.send(message);
        }
    }

    /**
    *
    * getter of session
    * @param sessionId
    *
    **/
    public ChatSession getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    /**
    *
    * getter of sessionkeys
    *
    **/
    public Set<String> getSessionKeys() {
        return sessions.keySet();
    }

    /**
    *
    * getter of sessions
    *
    **/
    public ChatSession[] getSessions() {
        return sessions.values().toArray(new ChatSession[0]);
    }

    /**
    *
    * @param sessionId
    *
    **/
    public void remove(String sessionId) {
        sessions.remove(sessionId);
    }

    /**
    *
    * getter of sessioncount
    *
    **/
    public int getSessionCount() {
        return sessions.size();
    }

    /**
    *
    * getter of chatsessionlistener
    *
    **/
    public ChatSessionListener getChatSessionListener() {
        return chatSessionListener;
    }

    /**
    *
    * setter of chatsessionlistener
    * @param chatSessionListener
    *
    **/
    public void setChatSessionListener(ChatSessionListener chatSessionListener) {
        this.chatSessionListener = chatSessionListener;
    }

    /**
    *
    * @param client
    * @param ctx
    *
    **/
    public ChatSession generateSession(String client, ChannelHandlerContext ctx) {
        ChatSession session = new ChatSession();
        session.setClient(ctx);
        session.setId(client);
        session.setChatSessionListener(chatSessionListener);

        session.connect();

        return session;
    }
}
