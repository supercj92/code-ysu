package com.cfysu.algorithm;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author canglong
 * @Date 2023/9/11
 */
public class ThreadWait {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger total = new AtomicInteger(100);
        Object lock = new Object();
        new Thread(new Task(total, Task.EVEN_MODE, lock)).start();
        new Thread(new Task(total, Task.ODD_MODE, lock)).start();
    }

    public static class Task implements Runnable {

        private AtomicInteger count;
        private String mode;

        private final Object lock;

        //奇数
        public static final String ODD_MODE = "odd";
        //偶数
        public static final String EVEN_MODE = "even";

        public Task(AtomicInteger count, String mode, Object lock) throws InterruptedException {
            this.count = count;
            this.mode = mode;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (count.get() > 0) {
                try {
                    synchronized (lock) {
                        int remainder = count.get() % 2;
                        if (remainder == 0 && EVEN_MODE.equals(mode)) {
                            System.out.println(
                                String.format("%s %s : %s", Thread.currentThread().getName(), mode,
                                    count.getAndDecrement()));
                            try {
                                lock.wait(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        if (remainder != 0 && ODD_MODE.equals(mode)) {
                            System.out.println(
                                String.format("%s %s : %s", Thread.currentThread().getName(), mode,
                                    count.getAndDecrement()));
                            try {
                                lock.wait(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
