package com.cfysu.thread.sync;


import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private final ReentrantLock lock = new ReentrantLock();

    private Integer balance;

    public Account(Integer balance){
        this.balance = balance;
    }

    public void takeMoneyFromAccount(Integer takeNum){
        lock.lock();
        try {
            if(takeNum <= balance){
                //让出cpu，使其他线程有机会执行
                Thread.sleep(1);
                balance -= takeNum;
                System.out.println(Thread.currentThread().getName() + "取钱成功。takeNUm:" + takeNum + "。余额：" + balance);
            }else {
                System.out.println(Thread.currentThread().getName() + "取钱失败，余额不足。balance:" + balance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

}
