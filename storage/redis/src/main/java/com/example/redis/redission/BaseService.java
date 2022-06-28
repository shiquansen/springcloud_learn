package com.example.redis.redission;

import org.redisson.api.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseService {
    @Resource
    private RedissonClient redissonClient;

    private void getBase(){
        RKeys keys = redissonClient.getKeys();
        //五种基本数据结构
        RBucket<Object> bucket = redissonClient.getBucket("");
        RList<Object> list = redissonClient.getList("");
        RMap<Object, Object> map = redissonClient.getMap("");
        RSet<Object> set = redissonClient.getSet("");
        RScoredSortedSet<Object> scoredSortedSet = redissonClient.getScoredSortedSet("");

        //高级属性
        RTopic topic = redissonClient.getTopic("");
        RBatch batch = redissonClient.createBatch(BatchOptions.defaults());

        RBitSet bitSet = redissonClient.getBitSet("");
        RHyperLogLog<Object> hyperLogLog = redissonClient.getHyperLogLog("");
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("");
        RStream<Object, Object> stream = redissonClient.getStream("");
        RGeo<Object> geo = redissonClient.getGeo("");


        RTransaction transaction = redissonClient.createTransaction(TransactionOptions.defaults());

        //分布式并发数据结构
        RLock lock = redissonClient.getLock("");
        RLock fairLock = redissonClient.getFairLock("");
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("");

        RAtomicLong atomicLong = redissonClient.getAtomicLong("");
        RBlockingDeque<Object> blockingDeque = redissonClient.getBlockingDeque("");
        RAtomicDouble atomicDouble = redissonClient.getAtomicDouble("");
        RLongAdder longAdder = redissonClient.getLongAdder("");
        RDoubleAdder doubleAdder = redissonClient.getDoubleAdder("");
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch("");
        RScheduledExecutorService executorService = redissonClient.getExecutorService("");
        RSemaphore semaphore = redissonClient.getSemaphore("");
        RPermitExpirableSemaphore permitExpirableSemaphore = redissonClient.getPermitExpirableSemaphore("");

        //unknown
        RSetCache<Object> setCache = redissonClient.getSetCache("");
        RMapCache<Object, Object> mapCache = redissonClient.getMapCache("");
        RListMultimap<Object, Object> listMultimap = redissonClient.getListMultimap("");
        RListMultimapCache<Object, Object> listMultimapCache = redissonClient.getListMultimapCache("");
        RLocalCachedMap<Object, Object> localCachedMap = redissonClient.getLocalCachedMap("", LocalCachedMapOptions.defaults());
        RSetMultimap<Object, Object> setMultimap = redissonClient.getSetMultimap("");
        RSetMultimapCache<Object, Object> setMultimapCache = redissonClient.getSetMultimapCache("");

    }
}
