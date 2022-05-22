package com.example.redis.redission;

import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.redisson.client.protocol.RedisCommands;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisBase {

    @Autowired
    RedissonClient redissonClient;

    public void setKey(String key, String value){
        redissonClient.getBucket(key).set(value);
    }
}
