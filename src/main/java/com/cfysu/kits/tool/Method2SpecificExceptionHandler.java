package com.cfysu.kits.tool;

import com.alibaba.xlab.kits.diagnosis.core.ExceptionHandler;

import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2022/11/7
 */
@Component
public class Method2SpecificExceptionHandler implements ExceptionHandler<String> {
    @Override
    public String handle(Throwable throwable, Object[] objects) {
        return "specific logic";
    }
}
