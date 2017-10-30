package com.cfysu.thread;

/**
 * 线程A、线程B
 * 谁先获得锁是随机的
 */

public class ObjectWaitAndNotify {

    public static void main(String[] args){
        final Object object = new Object();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    for (int i = 0;i < 3;i++){
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                        if(i == 0){
                            try {
                                //A线程释放锁，进入等待池
                                System.out.println("A线程释放锁，进入等待池...");
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }, "threadA");

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    for (int i = 0;i < 3;i++){
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                    //唤醒A线程
                    System.out.println("唤醒A线程");
                    object.notify();
                }
            }
        }, "threadB");
        //谁先获得锁是随机的
        threadA.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();
    }
}
