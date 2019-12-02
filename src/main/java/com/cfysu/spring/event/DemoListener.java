package com.cfysu.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListener implements ApplicationListener<DemoEvent>{

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(DemoEvent event) {
        System.out.println("接收到事件：" + event.getMsg());
    }
}
