package com.cfysu.spring.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author canglong
 * @Date 2018/10/30
 */
@Configuration
public class ConfigBean {

    @Bean
    public ConfigService getConfigService(){
        return new ConfigService();
    }
}
