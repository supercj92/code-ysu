package com.cfysu.pattern.observer.mine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public class Trigger {

    public List<Listener> listenerList;

    public Trigger(){
        listenerList = new ArrayList<>();
    }

    public void triggerEvent(){
        for(Listener listener : listenerList){
            listener.onEvent();
        }
    }

    public void addListener(Listener listener){
        listenerList.add(listener);
    }
}
