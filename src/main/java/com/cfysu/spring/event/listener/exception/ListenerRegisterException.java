package com.cfysu.spring.event.listener.exception;

/**
 * @Author canglong
 * @Date 2019/11/28
 */
public class ListenerRegisterException extends RuntimeException{
    public ListenerRegisterException(String reason){
        super(reason);
    }
}
