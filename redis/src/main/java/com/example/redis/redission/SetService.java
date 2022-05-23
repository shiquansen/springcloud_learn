package com.example.redis.redission;

import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SetService<T>{
    @Resource
    private RedissonClient redissonClient;

    public void set(String mapKey, T value){
        RSet<T> set = redissonClient.getSet(mapKey);
        set.add(value);
    }


    public boolean remove(String mapKey, T key){
        RSet<T> set = redissonClient.getSet(mapKey);
        return set.remove(key);
    }

    public Set<T> delete(String mapKey, String key){
        RSet<T> set = redissonClient.getSet(mapKey);
        return set.readAll();
    }

    public boolean expire(String mapKey, Long time){
        RSet<T> set = redissonClient.getSet(mapKey);
        return set.expire(time, TimeUnit.SECONDS);
    }
}
