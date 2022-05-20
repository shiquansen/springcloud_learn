package com.example.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
//    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
//                .setConnectTimeout(timeout) // 未设置使用默认
//                .setDatabase(redisProperties.getDatabase()) // 未设置使用默认
//                .setPassword(redisProperties.getPassword()); // 未设置使用默认
        return Redisson.create(config);
    }
}
