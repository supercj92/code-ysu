package com.cfysu.proxy.jdk;

import org.springframework.stereotype.Service;

@Service
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
