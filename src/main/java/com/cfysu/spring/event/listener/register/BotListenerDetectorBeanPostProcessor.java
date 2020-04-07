package com.cfysu.spring.event.listener.register;

import com.cfysu.spring.event.listener.exception.ListenerRegisterException;
import com.cfysu.spring.event.listener.listener.BotEventListener;
import com.cfysu.spring.event.listener.listener.BotListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2019/11/27
 */
@Component
public class BotListenerDetectorBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private ListenerRegister botEventPublisher;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        BotEventListener annotation = AnnotationUtils.getAnnotation(bean.getClass(), BotEventListener.class);
        if(annotation != null){
            if(!BotListener.class.isAssignableFrom(bean.getClass())){
                throw new ListenerRegisterException(beanName + " must implements BotListener");
            }
            //register Listener
            botEventPublisher.addListener((BotListener)bean);
            return bean;
        }
        return bean;
    }
}
