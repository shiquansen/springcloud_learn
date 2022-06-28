package com.example.redis.redission;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class StringService<T> {

    @Autowired
    RedissonClient redissonClient;

    /**
     * set
     * @param key key
     * @param value value
     */
    public void setKey(String key, String value, Long second){
        redissonClient.getBucket(key).set(value, second, TimeUnit.SECONDS);
    }

    /**
     * setNX
     * @param key key
     * @param value value
     */
    public void setNxKey(String key, String value, Long second){
        redissonClient.getBucket(key).setIfExists(value, second, TimeUnit.SECONDS);
    }

    /**
     * get
     * @param key key
     */
    public T setNxKey(String key){
        RBucket<T> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    /**
     * 删除key
     * @param t t
     * @return delete
     */
    public boolean delete(String t){
        RBucket<T> bucket = redissonClient.getBucket(t);
        return bucket.delete();
    }
}
