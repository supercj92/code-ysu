package com.cfysu.pattern.observer.mine;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public class Main {
    public static void main(String[] args) {
        Trigger trigger = new Trigger();

        trigger.addListener(new ListenerA());
        trigger.triggerEvent();

        trigger.addListener(new ListenerB());
        trigger.triggerEvent();
    }
}
