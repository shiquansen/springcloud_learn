package com.example.redis.base;

import com.example.redis.redission.RedisBase;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;


@SpringBootTest
class StringTests {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedisBase redisBase;

    @Autowired
    RedissonClient redissonClient;
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("sqs", "sqs");
    }

    @Test
    void contextLoads1() {
        redisBase.setKey("manjun", "123");
    }

    @Test
    void contextLoads2() {
        Object manjun = redissonClient.getBucket("manjun").get();
        System.out.println(manjun);
    }

}
