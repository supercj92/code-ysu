package com.cfysu.lab.spring.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Author canglong
 * @Date 2022/1/13
 */
public class TemplateMain {
    public static void main(String[] args) {
        RestTemplate template = new RestTemplate();
        Map<String, String> vars = new HashMap<>();
        vars.put("k1", "v1");
        ResponseEntity<String> forEntity = template.getForEntity("http://www.taobao.com", String.class, vars);
        System.out.println();
    }
}
