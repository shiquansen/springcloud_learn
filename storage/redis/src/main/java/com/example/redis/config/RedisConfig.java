package com.example.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Bean
    public RedissonClient redisClient(){

        Config config =new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379")
            .setConnectTimeout(10000)
            .setDatabase(0);
        config.setCodec(new JsonJacksonCodec());
        return Redisson.create(config);
    }
}
