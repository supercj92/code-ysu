package com.cfysu.proxy;

public class DogImpl implements Dog{
    @Override
    public void bark(String str) {
        System.out.println("汪汪汪。。。" + str);
    }
}
