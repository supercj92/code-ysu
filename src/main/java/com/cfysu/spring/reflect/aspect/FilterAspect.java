package com.cfysu.spring.reflect.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2020/5/20
 */

@Order(1)
@Component
@Aspect
public class FilterAspect {

    @AfterReturning(value = "execution(* com.cfysu.spring.reflect.OperateDataService.*(..)))", returning = "retVal")
    public void doLog(JoinPoint point, Object retVal) throws Throwable {
        System.out.println("after returning...");
    }
}
