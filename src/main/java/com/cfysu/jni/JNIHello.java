package com.cfysu.jni;

/**
 * @Author canglong
 * @Date 2021/6/12
 * 生成头文件
 * javah -classpath /Users/chris/IdeaProjects/mine/code-ysu/src/main/java  -jni com.cfysu.jni.JNIHello
 */
public class JNIHello {
    {
        System.loadLibrary("HelloJNI2");
    }
    public native String sayHello();
}
