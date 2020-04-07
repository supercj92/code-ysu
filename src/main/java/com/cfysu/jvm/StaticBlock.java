package com.cfysu.jvm;

/**
 * @Author canglong
 * @Date 2019/12/24
 */
public class StaticBlock {
    static {
        int res = 1/0;
    }
    public static void main(String[] args) {
        System.out.println("main");
    }
}
