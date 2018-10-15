package com.cfysu.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DemoPublisher {

    @Autowired
    private ApplicationContext applicationContext;

    public void pubishEvent(String msg){
        applicationContext.publishEvent(new DemoEvent(this, msg));
    }
}
