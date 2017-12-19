package com.cfysu.aop;

import com.cfysu.reflect.Authorization;
import com.cfysu.reflect.PrivilegeAuthorization;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class AuthorizationAop {

    @Pointcut("execution (* com.cfysu.reflect.PrivilegeAuthorization.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object checkPrivilege(ProceedingJoinPoint point) throws Throwable {
        //使用反射获取注解中的参数
        Class clazz = PrivilegeAuthorization.class;
        Method dosth = clazz.getMethod("doSomeThing2");
        Authorization auth = dosth.getAnnotation(Authorization.class);
        String code = auth.value();

        if("admin".equals(code)){
            System.out.println("权限是管理员");
            Object object = point.proceed();
            return object;
        }else {
            System.out.println("权限不是管理员");
            return null;
        }

    }
}
