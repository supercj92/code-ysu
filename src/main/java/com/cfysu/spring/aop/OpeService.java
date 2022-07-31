package com.cfysu.spring.aop;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2019/7/12
 */
@Component
@EnableAspectJAutoProxy
public class OpeService {

    @AroundLog
    public String foo(String arg){
        System.out.println(arg);
        return arg + " res";
    }
}
