package com.cfysu.kits.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.xlab.kits.diagnosis.core.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2022/11/7
 */
@Slf4j
@Component
public class FooExceptionHandler implements ExceptionHandler<String> {
    @Override
    public String handle(Throwable throwable, Object[] args) {
        log.error(JSON.toJSONString(args), throwable);
        return "defaultVal";
    }
}
