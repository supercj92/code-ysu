package com.cfysu.velocity;

import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * @Author canglong
 * @Date 2021/8/6
 */
public class VelocityMain {
    public static void main(String[] args) {
        Properties properties=new Properties();
        //设置velocity资源加载方式为class
        properties.setProperty("resource.loader", "class");
        //设置velocity资源加载方式为file时的处理类
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        //实例化一个VelocityEngine对象
        VelocityEngine velocityEngine=new VelocityEngine(properties);
        velocityEngine.init();

        Template template = velocityEngine.getTemplate("com/cfysu/velocity/index.vm");
        VelocityContext context = new VelocityContext();

        context.put("name", "World");

        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);
        System.out.println(stringWriter.toString());
    }
}
