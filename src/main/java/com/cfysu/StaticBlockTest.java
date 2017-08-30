package com.cfysu;

/**
 * Created by cj on 2017/7/26.
 */
class Test{
    public static int X=100;
    public final static int Y = 200 * 2;
    public Test(){
        System.out.println("Test构造函数执行");
    }
    //类加载时会执行静态代码块
    static{
        System.out.println("static语句块执行");
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



        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
