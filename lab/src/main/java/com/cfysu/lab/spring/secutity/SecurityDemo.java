package com.cfysu.lab.spring.secutity;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author canglong
 * @Date 2023/10/12
 */
public class SecurityDemo {

    @Test
    public void testEncode() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
        String abc123 = passwordEncoder.encode("abc123");
        System.out.println(abc123);
    }
}
