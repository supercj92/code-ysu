package com.cfysu.spring.aop;

import com.cfysu.spring.reflect.Authorization;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class AuthorizationAop {

    @Pointcut("execution (* com.cfysu.spring.reflect.OperateData.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object checkPrivilege(ProceedingJoinPoint point) throws Throwable {
        //获取参数
        Object[] objects = point.getArgs();
        //使用反射获取注解中的参数
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Authorization authorization = method.getAnnotation(Authorization.class);
        if(authorization == null){
            System.out.println("未做权限控制");
            return point.proceed();
        }
        if("admin".equals(authorization.value())){
            System.out.println("此操作需要具有管理员权限才能执行");
            return point.proceed();
        }else if("visitor".equals(authorization.value())){
            System.out.println("此操作访客权限可以执行");
            return point.proceed();
        }else{
            System.out.println("无权限执行此操作");
            return null;
        }
    }
}
