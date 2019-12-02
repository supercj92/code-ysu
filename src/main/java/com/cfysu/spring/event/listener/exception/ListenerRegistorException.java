package com.cfysu.spring.event.listener.exception;

/**
 * @Author canglong
 * @Date 2019/11/28
 */
public class ListenerRegistorException extends RuntimeException{
    public ListenerRegistorException(String reason){
        super(reason);
    }
}
