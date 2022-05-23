package com.example.redis.redission;

import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.util.List;

public class ListService<T> {
    @Resource
    private RedissonClient redissonClient;

    /**
     * 获取索引下的列表
     * @param key key
     * @param indexes indexes
     * @return 列表
     */
    public List<T> getList(String key, int[] indexes){
        RList<T> list = redissonClient.getList(key);
        return list.get(indexes);
    }

    /**
     * set单个值
     * @param key key
     * @param index index
     * @param t t
     */
    public void setList(String key, int index, T t){
        RList<T> list = redissonClient.getList(key);
        list.set(index, t);
    }

    /**
     * 删除
     * @param key key
     */
    public void deleteList(String key){
        RList<T> list = redissonClient.getList(key);
        list.delete();
    }


    /**
     * 分页
     * @param key key
     * @param pageNo 页码
     * @param pageSize 页面大小
     */
    public void getPage(String key, int pageNo, int pageSize){
        RList<T> list = redissonClient.getList(key);
        list.range((pageNo-1) * pageSize, pageNo * pageSize);
    }

    /**
     * 添加数据
     * @param key key
     * @param t t
     */
    public void add(String key, T t){
        RList<T> list = redissonClient.getList(key);
        list.add(t);
    }

    /**
     * 移除数据
     * @param key key
     * @param t t
     */
    public void remove(String key, T t){
        RList<T> list = redissonClient.getList(key);
        list.remove(t);
    }

}
