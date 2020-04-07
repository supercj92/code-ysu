package com.cfysu.spring.reflect;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class FactoryMain {
    public static void main(String[] args) {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("ApplicationContext.xml"));
        OperateDataService operateDataMain = (OperateDataService)beanFactory.getBean("operateDataMain");
        operateDataMain.insertData();
    }

    @Test
    public void testDefaultBeanFactory(){
        ClassPathResource resource = new ClassPathResource("ApplicationContext.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        OperateDataService operateDataMain = (OperateDataService)beanFactory.getBean("operateDataMain");
        operateDataMain.insertData();
    }
}
