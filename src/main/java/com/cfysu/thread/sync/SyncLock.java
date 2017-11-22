package com.cfysu.thread.sync;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import java.util.TreeMap;

public class SyncLock implements Runnable{
    private static boolean staticFlag = true;

    @Override
    public void run(){
        if(staticFlag){
            staticFlag = false;
            //类锁
            synchronized (SyncLock.class){
                print();
            }
        }else {
            staticFlag = true;
            //对象锁
            synchronized (this){
                print();
            }
        }
    }

    private void print(){
        for(int i = 0;i < 10;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncLock syncLock = new SyncLock();
        new Thread(syncLock).start();

        Thread.sleep(1000);

        new Thread(syncLock).start();
    }
}
