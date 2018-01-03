package com.cfysu.thread;

public class ThreadInterruption {
    public static void main(String[] args){
        Thread three = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        three.start();
        System.out.println("thread three started");
        three.interrupt();
        System.out.println("interrupted");
    }
}
