package com.cfysu.spring.wire;

/**
 * @Author canglong
 * @Date 2022/7/14
 */
public interface Foo<A, B> {
    B bar(A a);
}
