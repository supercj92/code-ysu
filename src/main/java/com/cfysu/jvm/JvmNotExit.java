package com.cfysu.jvm;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public class JvmNotExit {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                }
            }
        }).start();
        System.out.println("__done__");
    }
}
