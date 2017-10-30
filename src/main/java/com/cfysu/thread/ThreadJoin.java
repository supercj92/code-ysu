package com.cfysu.thread;

public class ThreadJoin {

    //线程A、线程B顺序执行
    public static void main(String[] args){
        final Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 3;i++){
                    System.out.println("threadA:" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                //等待A执行结束再执行B
                try {
                    System.out.println("等待线程A执行结束...");
                    threadA.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0;i < 3;i++){
                    System.out.println("threadB:" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
