package com.cfysu.spring.context.constructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author canglong
 * @Date 2021/3/10
 */
@Configuration
@Import(ComponentHandler.class)
/**
 * @import 注解使用在普通bean上，跟在类上直接使用@component注解效果一样
 */
public class AutoConfiguration {

    @Bean
    public Repository<Nlu> nluRepository(){
        return new Repository<>("nluRepository");
    }

    @Bean
    public Repository<Nlg> nlgRepository(){
        return new Repository<>("nlgRepository");
    }
}
