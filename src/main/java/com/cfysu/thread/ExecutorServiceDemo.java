package com.cfysu.thread;

import java.util.TreeMap;
import java.util.concurrent.*;

/**
 * Created by cj on 2017/8/24.
 */
public class ExecutorServiceDemo {

    public static void main(String[] args){
        System.out.println("主线程开始执行，线程name:" + Thread.currentThread().getName());
        //1.runnable方式启动线程
        //new Thread(new RunnableThread()).start();
        //2.使用ExecutorService开启线程
        ExecutorService pool = Executors.newFixedThreadPool(4);
        //pool.submit(new RunnableThread());
        //pool.submit(new RunnableThread());
        //pool.shutdown();
        //3.使用ExecutorService开启Callable
        Callable<Integer> thread = new CallableThread();
        Future<Integer> callTread = pool.submit(thread);
        Future<?> runTread = pool.submit(new RunnableThread());
        FutureTask<Integer> task = new FutureTask<Integer>(thread);
        new Thread(task).start();
        /*try {
            //此方法会阻塞主线程，等待其他线程执行结束
            //Integer res1 = callTread.get();
            //System.out.println("callTread res1:" + res1);
            //System.out.println("runTread res2:" + runTread.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程执行结束，线程name:" + Thread.currentThread().getName());
    }
}

class RunnableThread implements Runnable{

    @Override
    public void run() {
        int count = 0;
        boolean flag = true;
        while (flag){
            System.out.println("runnable执行次数:" + ++count + "次----is running----:" + Thread.currentThread().getName());
            if(count == 10){
                flag = false;
                System.out.println("run线程即将执行结束");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * runnable无返回值，callable有返回值
 */
class CallableThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("进入call线程体，线程name:" + Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("call线程体逻辑执行完毕，线程name:" + Thread.currentThread().getName());
        return 1;
    }
}