package com.cfysu.lab.spring.redis;

import org.junit.Test;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Author canglong
 * @Date 2023/8/9
 */
public class RedisMain {

    @Test
    public void testRedis() {
        //LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(SENTINEL_CONFIG,
        //    LettuceClientConfiguration.defaultConfiguration());
        //lettuceConnectionFactory.afterPropertiesSet();

        JedisConnectionFactory jedisConFactory
            = new JedisConnectionFactory();
        jedisConFactory.setHostName("localhost");
        jedisConFactory.setPort(36379);
        jedisConFactory.afterPropertiesSet();

        StringRedisTemplate template = new StringRedisTemplate(jedisConFactory);

        template.opsForValue().set("loop-forever", "0");
    }
}
