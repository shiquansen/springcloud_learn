package com.example.redis.redission;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class bloomFilter<T> {

    @Resource
    private RedissonClient redissonClient;

    public boolean add(String key, T value){
        RBloomFilter<T> bloomFilter = redissonClient.getBloomFilter(key);
        return bloomFilter.add(value);
    }

    public long count(String key){
        RBloomFilter<T> bloomFilter = redissonClient.getBloomFilter(key);
        return bloomFilter.count();
    }
    public boolean contains(String key, T value){
        RBloomFilter<T> bloomFilter = redissonClient.getBloomFilter(key);
        return bloomFilter.contains(value);
    }

    //          bloomFilter.tryInit()                   初始化布隆过滤器，var1表示大小，var2表示容错率
    //          bloomFilter.getSize()
    //          bloomFilter.getExpectedInsertions();    预计插入数量
    //          bloomFilter.getFalseProbability();      容错率
    //          bloomFilter.getHashIterations();        hash函数个数

}
