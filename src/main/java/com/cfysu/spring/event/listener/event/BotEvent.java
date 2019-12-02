package com.cfysu.spring.event.listener.event;

import java.util.EventObject;

/**
 * @Author canglong
 * @Date 2019/11/27
 */
public abstract class BotEvent extends EventObject {

    public BotEvent(Object source) {
        super(source);
    }
}
