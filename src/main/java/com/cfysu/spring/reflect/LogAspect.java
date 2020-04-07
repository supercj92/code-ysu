package com.cfysu.spring.reflect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    @Around(value = "execution(* com.cfysu.spring.reflect.OperateDataService.*(..)))")
    public void doLog(ProceedingJoinPoint point) throws Throwable {
        System.out.println("start log...");
        point.proceed();
        System.out.println("end log...");
    }
}
