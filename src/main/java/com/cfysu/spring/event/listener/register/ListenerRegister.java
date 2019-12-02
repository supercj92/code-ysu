package com.cfysu.spring.event.listener.register;

import com.cfysu.spring.event.listener.listener.BotListener;

/**
 * @Author canglong
 * @Date 2019/11/27
 */
public interface ListenerRegister {

    void addListener(BotListener listener);
}
