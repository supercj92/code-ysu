package com.cfysu.spring.reflect;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author canglong
 * @Date 2020/2/19
 */
public class OperateDataMain {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ConfigBean.class);
        OperateDataService auth = (OperateDataService)beanFactory.getBean("operateDataService");
        auth.updateData();
        auth.selectData();
        auth.deleteData();
        auth.insertData();
    }
}
