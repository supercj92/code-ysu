package com.cfysu.thread;

public class ThreadInterruption {
    public static void main(String[] args) throws Exception{
        Thread three = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;
                while (true){
                    try {
                        System.out.println("three:" + counter++);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        }, "thread-three");
        three.start();
        System.out.println("thread three started");

        Thread.sleep(10000);
        three.interrupt();
        System.out.println("interrupted");
    }
}
