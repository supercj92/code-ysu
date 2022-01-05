package com.cfysu.thread.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolMain {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger taskCount = new AtomicInteger(1);
        ThreadPoolMain instance = new ThreadPoolMain();
        ExecutorService pool = instance.getPool();
        //for(int i = 0;i < 40;i++){
        //    pool.execute(() -> {
        //        //while (true){
        //        System.out.println(Thread.currentThread().getName() + ":doing work");
        //        try {
        //            Thread.sleep(500);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //        //}
        //        System.out.println(taskCount.getAndIncrement() + "==>>" + Thread.currentThread().getName() + ":结束工作");
        //    });
        //    //Thread.sleep(1000);
        //}
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Future<?> future = pool.submit(() -> {
            System.out.println("start log");
            while (true){

            }
            //try {
            //    countDownLatch.wait();
            //} catch (InterruptedException e) {
            //    System.out.println("");
            //    e.printStackTrace();
            //}
        });
        Thread.sleep(1000);
        future.cancel(true);
        System.out.println("shutdown start");
        pool.shutdown();
        pool.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println("shutdown done");
    }

    public ExecutorService getPool(){
        return new ThreadPoolExecutor(2, 5, 2000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new NamedThreadFactory("ThreadPool"));
    }

    public class NamedThreadFactory implements ThreadFactory{

        private final String prefix;

        private final AtomicInteger thradCount = new AtomicInteger(1);

        NamedThreadFactory(String prefix){
            this.prefix = prefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, prefix + "-" + thradCount.getAndIncrement());
        }
    }
}
