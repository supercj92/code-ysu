package com.cfysu.thread.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏类似与闭锁
 * 区别：栅栏可以reset，重复用，闭锁是一次性的
 * 当所有的线程都到达一致状态（最后一个线程调用await），才会执行后续处理。
 */
public class BarrierDemo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        int threadNum = 4;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum + 1);

        for(int i = 0;i < threadNum;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":after await()");
                }
            }).start();
        }

        Thread.sleep(5000);

        cyclicBarrier.await();
        System.out.println(Thread.currentThread().getName() + ":after await()");
    }
}
