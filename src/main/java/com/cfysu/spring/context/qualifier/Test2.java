package com.cfysu.spring.context.qualifier;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2021/5/10
 */
@Order(100)
@Component
public class Test2 implements Test{
}
