package com.cfysu.socket.netty.chatroom.message;

import com.alibaba.fastjson.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: wangdong
 * Date: 14-8-26
 * To change this template use File | Settings | File Templates.
 */
public class ChatMessageUtils {

    class CMD {
        public static final String HEARTBEAT = "heartbeat";

        public static final String REGISTER = "register";

        public static final String UNREGISTER = "unregister";

        public static final String BROADCAST = "broadcast";

        public static final String TO = "to";

        public static final String GETALL = "getall";
    }

    /**
    *
    * @param message
    *
    **/
    public static boolean isHeartBeat(ChatMessage message) {
        return CMD.HEARTBEAT.equals(message.getCmd());
    }

    /**
    *
    * @param message
    *
    **/
    public static boolean isRegister(ChatMessage message) {
        return CMD.REGISTER.equals(message.getCmd());
    }

    /**
    *
    * @param message
    *
    **/
    public static boolean isUnRegister(ChatMessage message) {
        return CMD.UNREGISTER.equals(message.getCmd());
    }

    /**
    *
    * @param message
    *
    **/
    public static boolean isBroadcast(ChatMessage message) {
        return CMD.BROADCAST.equals(message.getCmd());
    }

    /**
    *
    * @param message
    *
    **/
    public static boolean isTo(ChatMessage message) {
        return CMD.TO.equals(message.getCmd());
    }

    /**
    *
    * @param message
    *
    **/
    public static boolean isGetAll(ChatMessage message) {
        return CMD.GETALL.equals(message.getCmd());
    }

    //{"id":"1", "token":"", "from":"32", "to":"12", "clientType":"android", "clientVersion":"1.1.0", "cmd":"heartbeat", "body":"werewfsfadfadfadsfadfaf"}
    /**
    *
    * @param message
    *
    **/
    public static ChatMessage generateMessage(String message) {

        return JSONObject.parseObject(message, ChatMessage.class);
    }

    /**
    *
    * @param message
    *
    **/
    public static String parseMessage(ChatMessage message) {

        return JSONObject.toJSONString(message) + System.getProperty("line.separator");
    }

    /**
    *
    * @param from
    *
    **/
    public static String generateRegisterMessage(String from) {
        ChatMessage message = new ChatMessage();
        message.setFrom(from);
        message.setId("1");
        message.setClientType("android");
        message.setClientVersion("1.1.0");
        message.setCmd(CMD.REGISTER);

        return parseMessage(message);
    }

    /**
    *
    * @param from
    *
    **/
    public static String generateUnRegisterMessage(String from) {
        ChatMessage message = new ChatMessage();
        message.setFrom(from);
        message.setId("1");
        message.setClientType("android");
        message.setClientVersion("1.1.0");
        message.setCmd(CMD.UNREGISTER);

        return parseMessage(message);
    }

    /**
    *
    * @param from
    * @param to
    * @param body
    *
    **/
    public static String generateToMessage(String from, String to, String body) {
        ChatMessage message = new ChatMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setId("1");
        message.setClientType("android");
        message.setClientVersion("1.1.0");
        message.setCmd(CMD.TO);
        message.setBody(body);

        return parseMessage(message);
    }

    /**
    *
    * @param from
    * @param body
    *
    **/
    public static String generateBroadcastMessage(String from, String body) {
        ChatMessage message = new ChatMessage();
        message.setFrom(from);
        message.setId("1");
        message.setClientType("android");
        message.setClientVersion("1.1.0");
        message.setCmd(CMD.BROADCAST);
        message.setBody(body);

        return parseMessage(message);
    }

    /**
    *
    * @param from
    *
    **/
    public static String generateHeartbeatMessage(String from) {
        ChatMessage message = new ChatMessage();
        message.setFrom(from);
        message.setId("1");
        message.setClientType("android");
        message.setClientVersion("1.1.0");
        message.setCmd(CMD.HEARTBEAT);

        return parseMessage(message);
    }

    /**
    *
    * @param from
    *
    **/
    public static String generateGetAllMessage(String from) {
        ChatMessage message = new ChatMessage();
        message.setFrom(from);
        message.setId("1");
        message.setClientType("android");
        message.setClientVersion("1.1.0");
        message.setCmd(CMD.GETALL);

        return parseMessage(message);
    }
}
