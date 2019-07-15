package com.cfysu.thread.threadlocal;

import com.cfysu.util.ThreadLocalVariable;

/**
 * @Author canglong
 * @Date 2019/5/9
 */
public class InheritThread {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocalVariable.setBrandId(12345L);

        new Thread(() -> {
            int count = 0;
            while(true){
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("" + count++ + Thread.currentThread().getName() + ":" + ThreadLocalVariable.getBrandId());
            }
        }
        ).start();

        Thread.sleep(1000L);
        ThreadLocalVariable.clear();
    }
}
