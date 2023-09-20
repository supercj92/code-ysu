package com.cfysu.midware.cache;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by cj on 17-6-25.
 */
public class RedisTest {

    private Jedis jedis;

    @Before
    public void setUp(){
        jedis = new Jedis("127.0.0.1",36379);
    }

    @Test
    public void testJedis(){
        jedis.set("test", "hello world");
        System.out.println("from redis:" + jedis.get("test"));
        System.out.println(jedis.exists("spring:session:sessions:3ec77be2-7ac6-4986-a67e-81b1475b3efd"));
    }
}
