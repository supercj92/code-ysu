package com.cfysu.jvm;

/**
 * Created by cj on 2017/9/4.
 */
public class JvmArgs {
    //java -Dglobal.config.path=/home/cj com.cfysu.jvm.JvmArgs
    public static void main(String[] args){
        String customArgs = System.getProperty("global.config.path");
        System.out.println("jvm var " + customArgs);
    }
}
