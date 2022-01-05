package com.cfysu.thread.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 多个线程从一个共享的令牌池里获取令牌，当无令牌可用的时候，线程必须等待其他线程释放令牌
 */
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        int threadNum = 4;
        final Semaphore semaphore = new Semaphore(threadNum - 1);
        for(int i = 0;i < threadNum;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(String.format("%s got token", Thread.currentThread().getName()));
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //如果不释放信号量，会有一个线程阻塞，jvm无法退出
                        semaphore.release();
                    }
                    System.out.println(Thread.currentThread().getName() + ":finished");
                }
            }).start();
        }
        System.out.println(Thread.currentThread().getName() + ":finished");
    }
}
