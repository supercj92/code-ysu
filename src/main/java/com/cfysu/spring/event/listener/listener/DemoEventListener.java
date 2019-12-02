package com.cfysu.spring.event.listener.listener;

import com.cfysu.spring.event.listener.event.FlowStartEvent;
import org.springframework.core.annotation.Order;

/**
 * @Author canglong
 * @Date 2019/11/27
 */
@Order(2)
@BotEventListener
public class DemoEventListener implements BotListener<FlowStartEvent> {
    @Override
    public void onEvent(FlowStartEvent event) {
        System.out.println("DemoEventListener");
    }
}
