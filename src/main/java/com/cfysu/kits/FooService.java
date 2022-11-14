package com.cfysu.kits;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author canglong
 * @Date 2022/11/7
 */
@Slf4j
public class FooService {
    String method1(String arg) {
        log.info(arg);
        String res = null;
        try {
            //do some calculate
            res = bizLogic(arg);
        } catch (Exception e) {
            log.error("bizLogic" + arg, e);
        }
        log.info(res);
        return res;
    }

    String method2(String arg) {
        log.info(arg);
        String res = null;
        try {
            //do some calculate
            res = bizLogic(arg);
        } catch (Exception e) {
            log.error("bizLogic" + arg, e);
        }
        log.info(res);
        return res;
    }

    String method3(String arg) {
        log.info(arg);
        String res = null;
        try {
            //do some calculate
            res = bizLogic(arg);
        } catch (Exception e) {
            log.error("bizLogic" + arg, e);
        }
        log.info(res);
        return res;
    }

    String bizLogic(String arg) {
        return "bizLogic";
    }
}
