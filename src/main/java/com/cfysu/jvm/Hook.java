package com.cfysu.jvm;

public class Hook {
    public static void main(String[] args){
        System.out.println("jvm started and shutdowning.hook is running...");
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("hook is running..." + ++count);
                    if(count == 5){
                        break;
                    }
                }
            }
        }));
    }
}
