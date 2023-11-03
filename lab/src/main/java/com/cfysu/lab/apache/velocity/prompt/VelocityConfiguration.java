package com.cfysu.lab.apache.velocity.prompt;

import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author canglong
 * @Date 2023/5/23
 */
@Configuration
public class VelocityConfiguration {

    @Bean
    public VelocityEngine velocityEngine() {
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "string");
        properties.setProperty("string.resource.loader.class",
            "org.apache.velocity.runtime.resource.loader.StringResourceLoader");
        properties.setProperty("string.resource.loader.modification.checkinterval", "10");
        properties.setProperty("string.resource.loader.repository.class",
            "com.alibaba.iic.alime.llm.prompt.velocity.DatabaseTemplateRepository");  //这是自定义的获取模板实现类
        properties.setProperty("runtime.custom_directives",
            "com.alibaba.iic.alime.llm.prompt.velocity.GroupTemplateDirective,com.alibaba.iic.alime.llm.prompt"
                + ".velocity.RefTemplateDirective");
        properties.setProperty("eventhandler.referenceinsertion.class",
            "com.alibaba.iic.alime.llm.prompt.velocity.DefaultValueHandler");
        //实例化一个VelocityEngine对象
        VelocityEngine velocityEngine = new VelocityEngine(properties);
        velocityEngine.init();
        return velocityEngine;
    }
}
