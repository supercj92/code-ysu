package com.cfysu.spring.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2019/1/2
 */
@Component
public class BeanA extends BaseBean{
    @Autowired
    private BeanB beanB;
}
