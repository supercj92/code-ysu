package com.cfysu.jvm;

/**
 * @Author canglong
 * @Date 2019/3/29
 * 后台存在非daemon线程，jvm不会退出
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
