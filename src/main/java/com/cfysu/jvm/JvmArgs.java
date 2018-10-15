package com.cfysu.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cj on 2017/9/4.
 */
public class JvmArgs {
    public static void main(String[] args){
        JvmArgs jvmArgs = new JvmArgs();

        //jvmArgs.testHeapOutOfMemory();

        //jvmArgs.testStackOverFlow();

        //jvmArgs.testStackOOM();
        jvmArgs.testPermGenOOM();

    }

    /**
     * 设置虚拟机自定义参数
     * java -Dglobal.config.path=/home/cj com.cfysu.jvm.JvmArgs
     */
    public void testSetJvmArgs(){
        String customArgs = System.getProperty("global.config.path");
        System.out.println("jvm var " + customArgs);
    }




    /**
     * 演示堆内存溢出
     * -Xmx10M设置此参数jvm不会动态分配内存
     * javac JvmArgs.java
     * java -verbose:gc -Xms10M -Xmn10M -Xmx10M -XX:+PrintGCDetails -XX:ServivorRatio=8 com.cfysu.jvm.JvmArgs
     */
    public void testHeapOutOfMemory(){
        List<User> userList = new ArrayList<User>();
        while (true){
            userList.add(new User());
        }
    }

    /**
     * 演示栈内存溢出
     *
     */
    public void testStackOOM(){
        while (true){
            new Thread(new Runnable() {
                public void run() {
                    while (true){}
                }
            }).start();
        }
    }

    /**
     * 演示stackOverFlow
     * 设置堆大小
     * -Xss128k
     */
    public void testStackOverFlow(){
        testStackOverFlow();
    }

    /**
     * 演示方法区域异常
     * jdk1.6下运行，jdk1.7及以上方法区不在持久代
     * javac -encoding utf-8
     * java -XX:PermSize=10M -XX:MaxPermSize=10M
     */
    private void testPermGenOOM(){
        int i = 0;
        //使用list保持常量池引用，防止full gc回收常量池行为
        List<String> stringList = new ArrayList<String>();
        while (true){
            stringList.add(String.valueOf(i++).intern());
        }
    }

}

class User{
    private String name;
    private Long age;
    public String[] strArray;
}
