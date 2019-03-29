package com.cfysu.pattern.observer.mine;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public class ListenerA implements Listener{
    @Override
    public void onEvent() {
        System.out.println("a onEvent");
    }
}
