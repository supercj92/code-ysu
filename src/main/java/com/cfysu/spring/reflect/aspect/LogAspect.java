package com.cfysu.spring.reflect.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
@Aspect
public class LogAspect {

    @Around(value = "execution(* com.cfysu.spring.reflect.OperateDataService.*(..)))")
    public Object doLog(ProceedingJoinPoint point) throws Throwable {
        System.out.println("start log...");
        Object result = point.proceed();
        System.out.println("end log...");
        return result;
    }
}
