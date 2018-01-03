package com.cfysu.jvm;


public class Reordering {
    static int a = 0,b = 0,x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
               stepOne : a = 1;
               stepTwo : x = b;
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
               stepA : b = 1;
               stepB : y = a;
            }
        });

        one.start();
        two.start();
        one.join();
        two.join();

        //多次运行x,y的值是不一样的。因为指令重排序,stepOne,stepTwo执行顺序不一定
        System.out.println(String.format("x=%d,y=%d", x,y));
    }
}
