package com.cfysu.spring.reflect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author canglong
 * @Date 2020/2/20
 */
@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = {"com.cfysu.spring.reflect"})
public class ConfigBean {

    @Bean
    public OperateDataService testDataBean(){
        return new OperateDataService();
    }
}
