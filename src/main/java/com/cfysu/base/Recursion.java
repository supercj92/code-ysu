package com.cfysu.base;

/**
 * @Author canglong
 * @Date 2020/2/24
 */
public class Recursion {

    public static void main(String[] args) {

    }

    /**
     * 5! = 5 * 4 * 3 * 2 * 1
     * @param num 数字
     * @return num的阶乘
     */
    public static int factorial(int num){
        if(num == 1){
            return 1;
        }

        return num * factorial(num - 1);
    }

    /**
     * 0 1 1 2 3 5 8 ...
     * @param num 斐波那契数列的位数
     * @return num位斐波那契数列的和
     */
    public static int fibonacci(int num){
        if(num == 0){
            return 0;
        }
        if(num == 1){
            return 1;
        }
        return num + fibonacci(num - 1);
    }
}
