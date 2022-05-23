package com.example.redis.redission;

import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.SortedSet;

public class ZSetService <T>{

    @Resource
    private RedissonClient redissonClient;

    public void add(String mapKey, T value){
        SortedSet<T> set = redissonClient.getSortedSet(mapKey);
        set.add(value);
    }


    public boolean remove(String mapKey, T key){
        SortedSet<T> set = redissonClient.getSortedSet(mapKey);
        return set.remove(key);
    }

    public T getFirst(String mapKey, String key){
        SortedSet<T> set = redissonClient.getSortedSet(mapKey);
        return set.first();
    }

  //---------------------------------------------------------

    public Integer add(String mapKey,double score, T value){
        RScoredSortedSet<T> set = redissonClient.getScoredSortedSet(mapKey);
        return set.addAndGetRank(score, value);
    }

    public boolean removeScored(String mapKey, T key){
        RScoredSortedSet<T> set = redissonClient.getScoredSortedSet(mapKey);
        return set.remove(key);
    }

    public Double getScored(String mapKey, T key){
        RScoredSortedSet<T> set = redissonClient.getScoredSortedSet(mapKey);
        return set.getScore(key);
    }

    public Collection<T> getAllZSet(String mapKey, Integer start, Integer end){
        RScoredSortedSet<T> set = redissonClient.getScoredSortedSet(mapKey);
        return set.valueRange(start, end);
    }
}
