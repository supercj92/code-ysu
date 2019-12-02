package com.cfysu.spring.event.listener.listener;

import com.cfysu.spring.event.listener.event.FlowEndEvent;
import org.springframework.core.annotation.Order;

/**
 * @Author canglong
 * @Date 2019/11/27
 */
@Order(2)
@BotEventListener
public class DemoEventListener3 implements BotListener<FlowEndEvent> {
    @Override
    public void onEvent(FlowEndEvent event) {
        System.out.println("DemoEventListener3");
    }
}
