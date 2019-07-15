package com.cfysu.spi;

import java.util.ServiceLoader;

/**
 * @Author canglong
 * @Date 2019/7/15
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<NameProvider> providers = ServiceLoader.load(NameProvider.class);
        for(NameProvider nameProvider : providers){
            System.out.println(nameProvider.getName());
        }
    }
}
