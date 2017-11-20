package com.cfysu.reflect;

import com.cfysu.model.CoverAreaVo;
import com.cfysu.proxy.DogImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {

        Class clazz = DogImpl.class;
        Object o = clazz.newInstance();

        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field field : declaredFields){
            System.out.println("field:" + field);
        }

        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        System.out.println("val=" + name.get(o));

        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods){
            System.out.println("method:" + method);
        }

        Method bark = clazz.getMethod("bark", String.class);
        bark.invoke(o, new Object[]{"呵呵..."});

        //调用私有方法
        Method eat = clazz.getDeclaredMethod("eat", String.class);
        eat.setAccessible(true);
        eat.invoke(o, new Object[]{"狗粮..."});


    }
}
