package com.cfysu.jvm.classloader;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;
import sun.misc.Launcher;

import java.net.URL;
import java.util.ArrayList;

/**
 * @Author canglong
 * @Date 2019/3/19
 */
public class Main {
    public static void main(String[] args) {
        //root
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        ArrayList<URL> urlList = new ArrayList<>();
        for (URL url : urLs){
            urlList.add(url);
        }
        urlList.parallelStream().forEach((url) -> System.out.println(url));

        //ext
        String property = System.getProperty("java.ext.dirs");
        String[] urlArray = StringUtils.split(property, ":");
        for(String string : urlArray){
            System.out.println(string);
        }

        //app
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(JSONObject.toJSONString(contextClassLoader));
    }

    @Test
    public void testLamda() throws InterruptedException {
        ArrayList<Integer> numList = new ArrayList();
        for(int i = 0;i < 2;i++ ){
            numList.add(i);
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for(Integer integer : numList){
            Thread.sleep(1000);
        }

        System.out.println(stopWatch.getTime());
        stopWatch.stop();
        stopWatch.reset();
        stopWatch.start();

        numList.forEach((num) -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(stopWatch.getTime());
        stopWatch.stop();
        stopWatch.reset();
    }

    @Test
    public void testJson(){
        String str = (String)null;
        System.out.println(str);
//        String temp1 = JSONObject.parseObject("100", String.class);
//        System.out.println(temp1);
//        String temp2 = JSONObject.parseObject("hello world", String.class);
//        System.out.println(temp2);
    }
}
