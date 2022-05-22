package com.example.redis.redission;

import org.redisson.api.RHyperLogLog;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

/**
 * HyperLogLogService 节约成本用于去重只能统计数量
 * @param <T>
 */
@Service
public class HyperLogLogService<T> {

    @Resource
    private RedissonClient redissonClient;

    public void pfadd(String key, String value){
        RHyperLogLog<String> hyperLogLog = redissonClient.getHyperLogLog(key);
        hyperLogLog.add(value);
    }

    public long pfcount(String key){
        RHyperLogLog<T> hyperLogLog = redissonClient.getHyperLogLog(key);
        return hyperLogLog.count();
    }

    public long pfmerge(String key1, String key2){
        RHyperLogLog<String> hyperLogLog = redissonClient.getHyperLogLog(key1);
        hyperLogLog.mergeWith(key2);
        return hyperLogLog.count();
    }

    public long countWith(String key1, String key2){
        RHyperLogLog<String> hyperLogLog = redissonClient.getHyperLogLog(key1);
        return hyperLogLog.countWith(key1, key2);
    }

}
