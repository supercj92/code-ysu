package com.cfysu.jvm;

public class TestJavap {
    //javap -c TestJava.class
    //javap -v TestJava.class
    public static void main(String[] args){
        String a = "a";
        String b = "b";
        String c = a + b;
        while (true){
            //死循环导致cpu使用率飚高
        }
    }
}
