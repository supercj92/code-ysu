package com.cfysu.thread.threadlocal;

import com.cfysu.util.ThreadLocalVariable;

/**
 * @Author canglong
 * @Date 2019/5/9
 */
public class InheritThread {
    public static void main(String[] args) {
        ThreadLocalVariable.setBrandId(12345L);

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalVariable.getBrandId())).start();
    }
}
