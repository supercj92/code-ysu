package com.cfysu.socket.netty.chatroom.session;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: wangdong
 * Date: 14-8-19
 * To change this template use File | Settings | File Templates.
 */
public class ChatSession {
	private static Logger _logger = Logger.getLogger(ChatSession.class);
    private long creationTime = 0L;

    private volatile long lastAccessedTime = creationTime;

    private int maxInactiveInterval = 60;

    private ChatSessionListener chatSessionListener;

    private ChannelHandlerContext client;

    private String id;

    public void send(String msg) {
        if (client == null) {
			_logger.info("Channel is not inited~!");
            return;
        }
        client.writeAndFlush(generateRespond(msg));
    }

    /**
    *
    *
    **/
    public void connect() {
        if(chatSessionListener != null)
            chatSessionListener.sessionCreated();
    }

    /**
    *
    *
    **/
    public void disconnect() {
        if (client == null) {
			_logger.info("Channel is not inited~!");
            return;
        }
        client.close();

        if(chatSessionListener != null)
            chatSessionListener.sessionDestroyed();
    }

    /**
     * �������true����֤���Ự�Ѿ����ڣ���Ҫ��������
     *
     * @return
     */
    public boolean expire() {
        //A negative time indicates that the session should never time out.
        if (maxInactiveInterval < 0)
            return false;

        long timeNow = System.currentTimeMillis();
        int timeIdle = (int) ((timeNow - lastAccessedTime) / 1000L);
        if (timeIdle >= maxInactiveInterval) {
            return true;
        }

        return false;
    }

    /**
    *
    *
    **/
    public void access() {

        this.lastAccessedTime = System.currentTimeMillis();
    }


    private ByteBuf generateRespond(String message) {
        message = message + System.getProperty("line.separator");
        ByteBuf resp = null;
        try {
            resp = Unpooled.copiedBuffer(message.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            _logger.error("occr err.",e);
        }
        return resp;
    }

    /**
    *
    * setter of client
    * @param client
    *
    **/
    public void setClient(ChannelHandlerContext client) {
        this.client = client;
    }

    /**
    *
    * setter of id
    * @param id
    *
    **/
    public void setId(String id) {
        this.id = id;
    }

    /**
    *
    * getter of id
    *
    **/
    public String getId() {
        return id;
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
}
