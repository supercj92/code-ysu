package com.cfysu.proxy.jdk;

public class DogImpl implements Dog{

    @Override
    public void bark(String str) {
        System.out.println("汪汪汪。。。" + str);
    }

    @Override
    public void eat(String food){
        System.out.println("dog eat " + food);
    }
}
