package com.cfysu.spring.context;

import java.util.Map;

import com.cfysu.spring.context.constructor.ComponentHandler;
import com.cfysu.spring.context.constructor.Repository;
import com.cfysu.spring.context.qualifier.TestService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author canglong
 * @Date 2018/10/30
 */
public class ApplicationContext {

    @Test
    public void testAnnotationApplicationContext(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //applicationContext.register(ConfigBean.class);
        applicationContext.scan("com.cfysu.spring.context");
        applicationContext.refresh();
        BaseBean bean1 = applicationContext.getBean(BaseBean.class);
        BeanB bean2 = applicationContext.getBean(BeanB.class);
        ConfigService configService = applicationContext.getBean(ConfigService.class);
        BeanA beanA = applicationContext.getBean(BeanA.class);
        ComponentHandler bean = applicationContext.getBean(ComponentHandler.class);
        Map<String, Repository> beansOfType = applicationContext.getBeansOfType(Repository.class);
        TestService testService = (TestService)applicationContext.getBean("testService");
        System.out.println(testService.testList.get(0).getClass().getName());
        System.out.println("==done==");
    }
}
