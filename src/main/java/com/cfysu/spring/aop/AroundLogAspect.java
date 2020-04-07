package com.cfysu.spring.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2019/7/12
 */
//@Component
//@Aspect
public class AroundLogAspect {

    @Around(value = "@annotation(aroundLog)")
    public Object printDebugLog(ProceedingJoinPoint pjp, AroundLog aroundLog) throws Throwable {
        Object[] args = pjp.getArgs();
        Signature signature = pjp.getSignature();
        String methodName = signature.getName();
        System.out.println(methodName + ":\n" + JSONObject.toJSONString(args));
        Object result = pjp.proceed();
        System.out.println(methodName + ":\n" + result);
        return result;
    }
}
