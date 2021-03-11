package com.cfysu.thread.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 多个线程从一个共享的令牌池里获取令牌，当无令牌可用的时候，线程必须等待其他线程释放令牌
 */
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        int threadNum = 4;
        final Semaphore semaphore = new Semaphore(threadNum);
        for(int i = 0;i < threadNum;i++){
            new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //如果不释放信号量，会有一个线程阻塞，jvm无法退出
                    semaphore.release();
                }
                System.out.println(Thread.currentThread().getName() + ":after acquire()");
            }).start();
        }
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName() + ":after acquire()");
    }
}
