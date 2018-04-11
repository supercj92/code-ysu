package com.cfysu.thread;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSharing {

    /**
     * volatitle可以保证变量的内存可见性
     */
    volatile static boolean stopFlag = false;

    static int count = 0;

    static AtomicInteger countAtom = new AtomicInteger(0);

    /**
     * idea快捷键 psvm
     */
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        shardingCountLatch();
    }

    public static void sharingVar() throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopFlag){
                    System.out.println(i++);
                }
                System.out.println("backgroundThread stop");
            }
        });
        backgroundThread.start();
        Thread.sleep(1000);
        System.out.println("停止后台线程");
        stopFlag = true;
    }

    /**
     * 多个线程共享count，非原子得count++，会有并发问题
     * 多个线程共享contAtom，它保证了线程的安全性
     * 也可以考虑加锁，但是效率要低于Atomic(cas)
     * @throws BrokenBarrierException
     * @throws InterruptedException
     */
    public static void shardingCount() throws BrokenBarrierException, InterruptedException {

        final CyclicBarrier barrier = new CyclicBarrier(101);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    countAtom.getAndIncrement();
                    try {
                        barrier.await();//will block until the last one invoke await
                        System.out.println(Thread.currentThread().getName() + " is dead");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        barrier.await();
        System.out.println(Thread.currentThread().getName() + " is dead");
        System.out.println(count);
        System.out.println(countAtom.get());
    }

    public static void shardingCountLatch() throws BrokenBarrierException, InterruptedException {
        final CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    countAtom.getAndIncrement();
                    latch.countDown();//will not block
                    System.out.println(Thread.currentThread().getName() + " is dead");
                }
            }).start();
        }
        latch.await();
        System.out.println(Thread.currentThread().getName() + " is dead");
        System.out.println(count);
        System.out.println(countAtom.get());
    }
}
