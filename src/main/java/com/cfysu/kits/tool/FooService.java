package com.cfysu.kits.tool;

import com.alibaba.xlab.kits.diagnosis.core.EnableDebug;
import com.alibaba.xlab.kits.diagnosis.core.HandleException;

/**
 * @Author canglong
 * @Date 2022/11/7
 */

@HandleException(handler = FooExceptionHandler.class)
public class FooService {

    @EnableDebug
    String method1(String arg) {
        String res = bizLogic(arg);
        return res;
    }

    @EnableDebug(rt = false)
    @HandleException(handler = Method2SpecificExceptionHandler.class)
    String method2(String arg) {
        String res = bizLogic(arg);
        return res;
    }

    String bizLogic(String arg) {
        return "bizLogic";
    }
}
