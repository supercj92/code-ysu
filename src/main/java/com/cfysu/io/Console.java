package com.cfysu.io;

import java.util.Scanner;

public class Console {
    public static void main(String[] args){
        Console console = new Console();
        console.campare();
    }

    public void scan(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            System.out.println("读入内容：" + scanner.nextLine());
        }
    }

    public void campare(){
        String hello1 = new String("hello");
        String hello2 = new String("hello");
        System.out.println("res1:" + (hello1 == hello2));
        System.out.println("hashCode1:" + hello1.hashCode());
        System.out.println("hashCode2:" + hello2.hashCode());
        System.out.println(System.identityHashCode(hello1));
        System.out.println(System.identityHashCode(hello2));

        System.out.println("====================");

        String hello3 = "hello";
        String hello4 = "hello";
        System.out.println("res2:" + (hello3 == hello4));
        System.out.println("hashCode3:" + hello3.hashCode());
        System.out.println("hashCode4:" + hello4.hashCode());
        System.out.println(System.identityHashCode(hello3));
        System.out.println(System.identityHashCode(hello4));

        System.out.println("====================");

        String hello5 = "he" + "llo";
        String hello6 = "hel" + "lo";
        System.out.println("res3:" + (hello5 == hello6));
        System.out.println("hashCode5:" + hello5.hashCode());
        System.out.println("hashCode6:" + hello6.hashCode());
        System.out.println(System.identityHashCode(hello5));
        System.out.println(System.identityHashCode(hello6));

        System.out.println("====================");

        String he = "he";
        String hello7 = he + "llo";
        String hello8 = "hel" + "lo";
        System.out.println("res4:" + (hello7 == hello8));
        System.out.println("hashCode8:" + hello7.hashCode());
        System.out.println("hashCode9:" + hello8.hashCode());
        System.out.println(System.identityHashCode(hello7));
        System.out.println(System.identityHashCode(hello8));
    }
}
