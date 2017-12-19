package com.cfysu.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by cj on 2017/8/24.
 * 有死锁的问题
 * 线程通信：传统的wait()、使用condition、阻塞队列、管道流
 * */
public class ThreadCommunication {
    public static void main(String[] args) throws Exception{
        //生成账户
        Account account = new Account();
        Callable saveThread = new SaveThread(account);
        Callable takeThread = new TakeThread(account);
        ExecutorService pool = Executors.newFixedThreadPool(4);
        //三个存钱的线程
        pool.submit(saveThread);
        pool.submit(saveThread);
        pool.submit(saveThread);
        //一个取钱的线程
        pool.submit(takeThread);

        pool.awaitTermination(1, TimeUnit.MINUTES);
        pool.shutdown();
        System.out.println("主线程结束");
    }
}

class Account{

    /**
     * 存一次，取一次交替操作
     */

    private Integer balance = 0;

    private boolean isSaved = false;

    public synchronized void save(Integer num) throws InterruptedException {
        //已存则等待
        if(isSaved){
            //释放锁
            wait();
        }else {
            //存钱
            balance += num;
            isSaved = true;
            //唤醒其他线程
            notifyAll();
            System.out.println("isSaved:" + isSaved + ".存钱操作已经执行:" + Thread.currentThread().getName() + "当前余额:" + balance);
        }
    }

    public synchronized void take(Integer num) throws InterruptedException {
        //未存则等待
        if(!isSaved){
            wait();
        }else {
            //取钱
            balance -= num;
            isSaved = false;
            notifyAll();
            System.out.println("isSaved:" + isSaved + ".取钱操作已经执行:" + Thread.currentThread().getName() + "当前余额:" + balance);
        }
    }
}

class TakeThread implements Callable<Integer>{

    private Account account;

    public TakeThread(Account account){
        this.account = account;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("进入取钱线程:" + Thread.currentThread().getName());
        for(int i = 0;i < 10;i++){
            System.out.println("取钱的次数:" +i);
            account.take(100);
        }
        return null;
    }
}

class SaveThread implements Callable<Integer>{

    private Account account;

    public SaveThread(Account account){
        this.account = account;
    }
    @Override
    public Integer call() throws Exception {
        System.out.println("进入存钱线程:" + Thread.currentThread().getName());
        for (int i = 0;i < 10;i++){
            System.out.println(Thread.currentThread().getName() + "存钱次数:" + i);
            account.save(100);
        }
        return null;
    }
}