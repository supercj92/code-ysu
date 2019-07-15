package com.cfysu.thread;

/**
 * @Author canglong
 * @Date 2019/5/13
 */
public class ObjectNotify {
    public static void main(String[] args) {
        Object object = new Object();

        //IllegalMonitorStateException
        //同步块中才可以调用此方法
        object.notify();
    }
}
