package com.example.redis.redission;

import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class HashService<T>{
    @Resource
    private RedissonClient redissonClient;

    public void setKey(String mapKey, String key, T value){
        RMap<String, T> map = redissonClient.getMap(mapKey);
        map.put(key, value);
    }

    public void setNxKey(String mapKey, String key, T value){
        RMap<String, T> map = redissonClient.getMap(mapKey);
        map.putIfAbsent(key, value);
    }

    public T get(String mapKey, String key){
        RMap<String, T> map = redissonClient.getMap(mapKey);
        return map.get(key);
    }

    /**
     * 删除key
     * @param key t
     * @return delete
     */
    public T delete(String mapKey, String key){
        RMap<String, T> map = redissonClient.getMap(mapKey);
        return map.remove(key);
    }

    /**
     * 过期时间
     * @param mapKey t
     * @param time t
     * @return delete
     */
    public boolean expire(String mapKey, Long time){
        RMap<String, T> map = redissonClient.getMap(mapKey);
        return map.expire(time, TimeUnit.SECONDS);
    }
}
