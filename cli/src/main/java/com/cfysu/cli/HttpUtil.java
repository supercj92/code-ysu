package com.cfysu.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

/**
 * @Author canglong
 * @Date 2022/1/27
 */
@ShellComponent
public class HttpUtil {

    private RestTemplate restTemplate = new RestTemplate();

    @ShellMethod("执行get请求")
    public String get(String url){
        return restTemplate.getForObject(url, String.class);
    }

    @ShellMethod("执行post请求")
    public String post(String url){
        return "post";
    }
}
