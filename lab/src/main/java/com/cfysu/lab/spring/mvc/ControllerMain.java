package com.cfysu.lab.spring.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author canglong
 * @Date 2022/1/13
 */
@Controller
@SpringBootApplication(scanBasePackages = {"com.cfysu.lab.spring.mvc"})
public class ControllerMain {

    public static void main(String[] args) {
        SpringApplication.run(ControllerMain.class, args);
    }

    /**
     * 设置HttpResponse的header
     * @param request
     * @param httpServletResponse
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping(value = "/foo")
    public ResponseEntity<String> foo(HttpServletRequest request, HttpServletResponse httpServletResponse)
        throws IOException, ServletException {
        ServletInputStream inputStream = request.getInputStream();
        //Collection<Part> parts = request.getParts();
        //String string = IOUtils.toString(inputStream);
        //File file = new File("./http.log");
        //FileCopyUtils.copy(inputStream, new FileOutputStream(file));
        byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>("ok", httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/bar")
    @ResponseBody
    public Map<String, String> bar(){
        Map<String, String> map = new HashMap<>();
        map.put("key", "val");
        return map;
    }
}
