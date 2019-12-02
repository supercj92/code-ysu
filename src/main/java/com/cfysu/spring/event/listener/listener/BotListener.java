package com.cfysu.spring.event.listener.listener;

import com.cfysu.spring.event.listener.event.BotEvent;

/**
 * @Author canglong
 * @Date 2019/11/27
 */
public interface BotListener<E extends BotEvent> extends java.util.EventListener {
    void onEvent(E event);
}
