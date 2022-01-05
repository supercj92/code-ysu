package com.cfysu.spring.context.qualifier;

import java.util.Scanner;

import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author canglong
 * @Date 2021/5/10
 */
@Order(200)
@Component
public class Test1 implements Test{

    public static void main(String[] args) {
        System.out.println("---");
        String s = new Scanner(System.in).nextLine();
        System.out.println(s);
    }
}
