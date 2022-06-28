package com.example.redis.redission.cacheProblem.xuebeng;

/**
 * 1. 如果是同一时间失效， 从出发点开始，ttl加上random
 * 2. 如果是数据量大，将redis换成集群模式
 * 3. 如果有太多的请求冲库，可以以牺牲部分用户功能去限流，降级甚至熔断
 */
public class XueBeng {
}
