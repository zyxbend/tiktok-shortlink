package com.pddbend.shortlink.project.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: pddbend
 * @Date: 2023-11-17-下午2:59
 * @Description: 布隆过滤器配置
 */
@Configuration
public class RBloomFilterConfiguration {

    /**
     * 防止新建短链接查询数据库的布隆过滤器 
     */
    @Bean
    public RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter(RedissonClient redissonClient) {
        RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter = redissonClient.getBloomFilter("shortUriCreateCachePenetrationBloomFilter");
        shortUriCreateCachePenetrationBloomFilter.tryInit(100000000L, 0.001);
        return shortUriCreateCachePenetrationBloomFilter;
    }
}