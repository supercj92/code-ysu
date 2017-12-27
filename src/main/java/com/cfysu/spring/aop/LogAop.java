package com.cfysu.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAop {

    @Around(value = "execution(* com.cfysu.spring.reflect.OperateData.*(..)))")
    public void doLog(ProceedingJoinPoint point) throws Throwable {
        System.out.println("start log...");
        point.proceed();
        System.out.println("end log...");
    }
}
