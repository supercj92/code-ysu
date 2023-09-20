package com.cfysu.lab.apache.velocity;

import java.io.StringWriter;
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

        Template template = velocityEngine.getTemplate("com/cfysu/lab/apache/velocity/index.vm");
        VelocityContext context = new VelocityContext();

        context.put("name", "World");

        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);
        System.out.println(stringWriter.toString());

        //StringWriter stringWriter2 = new StringWriter();
        //VelocityContext context2 = new VelocityContext();
        //
        //JSONObject promptContext = new JSONObject();
        //JSONObject car = new JSONObject();
        //car.put("brand", "bmw");
        //
        //JSONArray houses = new JSONArray();
        //JSONObject house1 = new JSONObject();
        //house1.put("location", "hangzhou");
        //house1.put("size", "100平");
        //JSONObject house2 = new JSONObject();
        //house2.put("location", "shanghai");
        //house2.put("size", "200平");
        //houses.add(house1);
        //houses.add(house2);
        //
        //promptContext.put("name", "xiaoming");
        //promptContext.put("car", car);
        //promptContext.put("houses", houses);
        //
        //System.out.println(JSON.toJSONString(promptContext, SerializerFeature.PrettyFormat));
        //
        //context2.put("promptContext", promptContext);
        //velocityEngine.evaluate(context2, stringWriter2, "template_json2", "$promptContext.name有一辆车，品牌是$promptContext"
        //    + ".car.brand，有$promptContext.houses.size()房子。"
        //    + "#foreach($house in $promptContext.houses)"
        //    + "第$foreach.count套在$house.location，面积$house.size"
        //    + "#if ($foreach.count != $promptContext.houses.size())"
        //    + ","
        //    + "#else"
        //    + "。"
        //    + "#end"
        //    + "#end");
        //System.out.println(stringWriter2);
        //
        //Velocity.evaluate(context2, stringWriter2, "template_json2", "property from json : $promptContext.name");
        //
    }
}
