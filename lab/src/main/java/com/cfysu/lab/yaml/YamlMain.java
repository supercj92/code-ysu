package com.cfysu.lab.yaml;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 * @Author canglong
 * @Date 2023/7/11
 */
public class YamlMain {

    @Test
    public void testParse() throws IOException {
        Yaml yaml = new Yaml(new Constructor(Config.class));
        Config config = yaml.load(new ClassPathResource("config.yaml").getInputStream());
    }

    // 定义配置类
    public static class Config {
        public Map<String, String> server;
        public Map<String, String> database;
    }
}
