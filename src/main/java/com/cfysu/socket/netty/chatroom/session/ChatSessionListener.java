package com.cfysu.socket.netty.chatroom.session;

/**
*
*
**/
public interface ChatSessionListener {

	public void sessionCreated();
	
	public void sessionDestroyed();

}
