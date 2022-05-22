package com.example.redis.redission;

import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GEOService {
    @Resource
    private RedissonClient redissonClient;

    
}
