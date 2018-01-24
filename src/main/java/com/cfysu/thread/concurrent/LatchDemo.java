package com.cfysu.thread.concurrent;


import java.util.concurrent.CountDownLatch;

public class LatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int threadNum = 4;
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNum);

        for(int i =0;i < threadNum;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        endGate.countDown();
                    }
                    System.out.println(Thread.currentThread().getName() + ":task done");
                }
            }).start();
        }

        System.out.println("放行线程");
        //放行线程
        startGate.countDown();

        System.out.println("主线程等待");
        //等待线程执行结束，主线程再往下走
        endGate.await();
        System.out.println("主线程结束");
    }
}
