package com.example.redis.redission;

import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class KeyService<T> {

    @Resource
    private RedissonClient redissonClient;



    /**
     *  是否存在
     * @param t t
     * @return exist
     */
    public boolean exist(String t){
        RBucket<T> bucket = redissonClient.getBucket(t);
        return bucket.isExists();
    }

    /**
     *  设置过期时间
     * @param t t
     * @param second second
     * @return expire
     */
    public boolean expire(String t, Long second){
        RBucket<T> bucket = redissonClient.getBucket(t);
        return bucket.expire(second, TimeUnit.SECONDS);
    }

    /**
     *  查找keys,  内部scan， 每次10个。
     * KEYS * 匹配数据库中所有 key 。
     * KEYS h?llo 匹配 hello ， hallo 和 hxllo 等。
     * KEYS h*llo 匹配 hllo 和 heeeeello 等。
     * KEYS h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。
     * @param pattern pattern
     * @return expire
     */
    public  Iterable<String> keys(String pattern){
        RKeys keys = redissonClient.getKeys();
        return keys.getKeysByPattern(pattern);
    }

    /**
     * 重命名
     * @return value
     */
    public boolean rename(String oldValue, String newValue){
        RBucket<T> bucket = redissonClient.getBucket(oldValue);
        return bucket.renamenx(newValue);
    }

    /**
     * ttl TimeToLive
     * 存活时间 remainTimeToLive
     * @return value
     */
    public long ttl(String t){
        RBucket<T> bucket = redissonClient.getBucket(t);
        return bucket.remainTimeToLive();
    }

}
