package com.cfysu.spring.reflect;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class OperateData {

    public static void main(String[] args) throws NoSuchMethodException {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");
        OperateData auth = (OperateData)beanFactory.getBean("operateData");
        auth.updateData();
        auth.selectData();
        auth.deleteData();
        auth.insertData();
    }

    @Authorization("admin")
    public void updateData(){
        System.out.println("admin update data...");
    }

    @Authorization("visitor")
    public void selectData(){
        System.out.println("visitor select data...");
    }

    @Authorization("superAdmin")
    public void deleteData(){
        System.out.println("superAdmin delete from table...");
    }

    public void insertData(){
        System.out.println("insert data...");
    }
}
