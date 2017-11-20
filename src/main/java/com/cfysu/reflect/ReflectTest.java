package com.cfysu.reflect;

import com.cfysu.model.CoverAreaVo;
import com.cfysu.proxy.DogImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {
    public static void main(String[] args){
        Class clazz = DogImpl.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field field : declaredFields){
            System.out.println("field:" + field);
        }

        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods){
            System.out.println("method:" + method);
        }

        try {
            Method bark = clazz.getMethod("bark", String.class);
            Object o = clazz.newInstance();
            bark.invoke(o, new Object[]{"呵呵..."});

            //调用私有方法
            Method eat = clazz.getDeclaredMethod("eat", String.class);
            eat.setAccessible(true);
            eat.invoke(o, new Object[]{"狗粮..."});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
