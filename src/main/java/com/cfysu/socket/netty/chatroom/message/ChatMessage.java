package com.cfysu.socket.netty.chatroom.message;

/**
 * {"id":"1", "token":"", "from":"32", "to":"12", "clientType":"android", "clientVersion":"1.1.0", "cmd":"heartbeat", "body":"werewfsfadfadfadsfadfaf"}
 * <p/>
 * Created with IntelliJ IDEA.
 * User: wangdong
 * Date: 14-8-25
 * To change this template use File | Settings | File Templates.
 */
public class ChatMessage {

    //消息id
    private String id;

    //
    private String token;

    //发送方
    private String from;

    //接收方
    private String to;

    //客户端类型
    private String clientType;

    //客户端版本
    private String clientVersion;

    //消息类型
    private String cmd;

    //消息体
    private String body;

    public String getId() {
        return id;
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
    * getter of token
    *
    **/
    public String getToken() {
        return token;
    }

    /**
    *
    * setter of token
    * @param token
    *
    **/
    public void setToken(String token) {
        this.token = token;
    }

    /**
    *
    * getter of from
    *
    **/
    public String getFrom() {
        return from;
    }

    /**
    *
    * setter of from
    * @param from
    *
    **/
    public void setFrom(String from) {
        this.from = from;
    }

    /**
    *
    * getter of to
    *
    **/
    public String getTo() {
        return to;
    }

    /**
    *
    * setter of to
    * @param to
    *
    **/
    public void setTo(String to) {
        this.to = to;
    }

    /**
    *
    * getter of clienttype
    *
    **/
    public String getClientType() {
        return clientType;
    }

    /**
    *
    * setter of clienttype
    * @param clientType
    *
    **/
    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    /**
    *
    * getter of clientversion
    *
    **/
    public String getClientVersion() {
        return clientVersion;
    }

    /**
    *
    * setter of clientversion
    * @param clientVersion
    *
    **/
    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    /**
    *
    * getter of cmd
    *
    **/
    public String getCmd() {
        return cmd;
    }

    /**
    *
    * setter of cmd
    * @param cmd
    *
    **/
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    /**
    *
    * getter of body
    *
    **/
    public String getBody() {
        return body;
    }

    /**
    *
    * setter of body
    * @param body
    *
    **/
    public void setBody(String body) {
        this.body = body;
    }
}
