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

    @Around(value = "execution(* com.cfysu.spring.reflect.OperateDataService.*(..)))")
    public Object doLog(ProceedingJoinPoint point) throws Throwable {
        System.out.println("start log...");
        Object result = point.proceed();
        System.out.println("end log...");
        return result;
    }
}
