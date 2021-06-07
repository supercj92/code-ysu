package com.cfysu.spi.dubbo;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.extension.factory.SpiExtensionFactory;

/**
 * @Author canglong
 * @Date 2021/4/19
 * 试用下dubbo的spi机制
 * mvn配置的问题，resources/META-DATA/dubbo/下的文件没有copy到classes目标下，运行导致加载不到类。手动将配置copy classes目录下即可
 */
public class SpiMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        ExtensionLoader<Hello> extensionLoader = ExtensionLoader.getExtensionLoader(Hello.class);
        Hello hello = extensionLoader.getExtension("hello2");
        System.out.println(((HelloImpl2)hello).getTest());
        //new CountDownLatch(1).await();
    }
}
