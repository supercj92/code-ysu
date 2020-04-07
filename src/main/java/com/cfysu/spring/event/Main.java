package com.cfysu.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoConfig.class);
        DemoPublisher demoPublisher = applicationContext.getBean(DemoPublisher.class);
        demoPublisher.publishEvent("demoEvent go");
        //ListenerRegister.listenerList.forEach(BotListener::onEvent);
        //BotEventPublisher botEventPublisher = applicationContext.getBean(BotEventPublisher.class);
        //botEventPublisher.publishEvent(new FlowStartEvent(new Object()));
        //botEventPublisher.publishEvent(new FlowEndEvent(new Object()));
        //botEventPublisher.publishEvent(new FlowEndEvent(new Object()));
        //applicationContext.close();
    }
}
