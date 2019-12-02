package com.cfysu.spring.event.listener.publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cfysu.spring.event.listener.event.BotEvent;
import com.cfysu.spring.event.listener.listener.BotListener;
import com.cfysu.spring.event.listener.register.ListenerRegister;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2019/11/27
 */
@Component
public class BotEventPublisher implements ListenerRegister{

    private Set<BotListener> listenerList = new HashSet<>();

    private Map<String, List<BotListener>> eventAssignListenerCache = new HashMap<>();


    public void publishEvent(BotEvent event){
        //根据声明的事件类型对监听器进行分类
        List<BotListener> eventAssignListeners = retrieveListeners(event);
        if(CollectionUtils.isEmpty(eventAssignListeners)){
            return;
        }
        //todo 并行处理
        for(BotListener botListener : eventAssignListeners){
            invokeListener(botListener, event);
        }
    }

    @SuppressWarnings({"unchecked"})
    private void invokeListener(BotListener botListener, BotEvent botEvent){
        //todo handle exception
        botListener.onEvent(botEvent);
    }

    @Override
    public void addListener(BotListener listener) {
        listenerList.add(listener);
    }

    public List<BotListener> retrieveListeners(BotEvent botEvent){
        String eventClazzName = botEvent.getClass().getSimpleName();
        List<BotListener> eventAssignListenersFromCache = eventAssignListenerCache.get(eventClazzName);
        if(eventAssignListenersFromCache != null){
            return eventAssignListenersFromCache;
        }
        List<BotListener> eventAssignListeners = new ArrayList<>();
        for (BotListener listener : listenerList){
            if(supportsEvent(listener, botEvent)){
                eventAssignListeners.add(listener);
            }
        }
        //排序
        AnnotationAwareOrderComparator.sort(eventAssignListeners);
        eventAssignListenerCache.put(eventClazzName, eventAssignListeners);

        return eventAssignListeners;
    }

    public boolean supportsEvent(BotListener botListener, BotEvent botEvent){
        //listener
        ResolvableType declaredEventType = resolveDeclaredEventType(botListener.getClass());

        //event
        ResolvableType currentEvent = ResolvableType.forClass(botEvent.getClass());
        return (declaredEventType == null || declaredEventType.isAssignableFrom(currentEvent));
    }

    private ResolvableType resolveDeclaredEventType(Class<?> listenerType) {
        ResolvableType resolvableType = ResolvableType.forClass(listenerType).as(BotListener.class);
        if (resolvableType == null || !resolvableType.hasGenerics()) {
            return null;
        }
        return resolvableType.getGeneric();
    }
}
