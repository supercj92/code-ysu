package com.cfysu;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cj on 2017/7/26.
 */
class Test{
    public static int X=100;
    public final static int Y = 200 * 2;
    private static final Map<Integer, String> NUM_2_STR_MAP = new HashMap<>();

    public Test(){
        System.out.println("Test构造函数执行");
    }
    //类加载时会执行静态代码块
    static{
        NUM_2_STR_MAP.put(1, "一");
        System.out.println("static语句块执行");
    }

    //实例化对象时会执行，并且在构造函数之前执行
    {
        System.out.println("非静态初始化块执行。。。");
    }
    public static void display(){
        System.out.println("静态方法被执行");
    }
    public void display_1(){
        System.out.println("实例方法被执行");
    }
}
public class StaticBlockTest{
    public static void main(String args[]){
        try{

            //***************类加载的时机***********

            //1.用Class.forName()显式加载的时候
            //Class.forName("com.cfysu.Test");
            //1.1在虚拟机的生命周期中一个类只被加载一次
            //Class.forName("com.cfysu.Test");

            //2.实例化一个对象时
            //Test test = new Test();

            //3.访问静态方法时会加载类
            //Test.display();

            //4.访问类的静态变量时
            //System.out.println(Test.X);

            //4.1调用类的静态“常量”的时候，是不会加载类的（如果编译器可以计算出常量的值）
            //System.out.println(Test.Y);

            //Test test1 = new Test();
            //Test test2 = new Test();

            StringBuilder sbd = new StringBuilder("sbd");
            sbd.append((String) null);
            System.out.println(sbd.toString());

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
