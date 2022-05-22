package com.example.redis.redission;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;

public class CommonService {

    @Resource
    private RedissonClient redissonClient;

    public boolean delete(String t){
        RBucket bucket = redissonClient.getBucket(t);
        return bucket.delete();
    }

    public boolean deleteByKey(String key){
        RBucket bucket = redissonClient.getBucket(key);
        return bucket.delete();
    }


}
