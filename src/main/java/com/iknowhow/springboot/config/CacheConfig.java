package com.iknowhow.springboot.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    private static final String CACHE = "users";

    @Bean
    public Cache cacheManager() {
        CacheManager cacheManager = new ConcurrentMapCacheManager(CACHE);
        return new TransactionAwareCacheDecorator(cacheManager.getCache(CACHE));
    }
}
