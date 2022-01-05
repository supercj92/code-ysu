package com.cfysu.thread.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import lombok.SneakyThrows;

/**
 * @Author canglong
 * @Date 2021/9/9
 */
public class ReentrantDemo {

    @SneakyThrows
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread threadOne = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                reentrantLock.lock();
                try {
                    countDownLatch.await();
                    System.out.println("threadOne release lock");
                }finally {
                    reentrantLock.unlock();
                }
            }
        });
        threadOne.start();

        Thread threadTwo = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(1000);
                try {
                    System.out.println("theadTwo wait for lock");
                    reentrantLock.lock();
                    System.out.println("threadTwo get lock");
                }finally {
                    reentrantLock.unlock();
                }
            }
        });
        threadTwo.start();

        Thread threadThree = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(2000);
                try {
                    System.out.println("theadThree wait for lock");
                    reentrantLock.lock();
                    System.out.println("threadThree get lock");
                }finally {
                    reentrantLock.unlock();
                }
            }
        });
        threadThree.start();

        Thread.sleep(3000);
        countDownLatch.countDown();
    }

}
