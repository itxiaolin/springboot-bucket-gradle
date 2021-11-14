package com.itxiaolin.ehcache.service.impl;

import com.itxiaolin.ehcache.service.CacheService;
import com.itxiaolin.ehcache.utils.CacheUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述：
 *
 * @author lxq
 * @version 1.00
 * @Date 2020/12/10
 */
@Service
public class CacheServiceImpl implements CacheService {
    @Override
    public void put(String key, Object value) {
        CacheUtils.put(key,value);
    }

    @Override
    public Object get(String key) {
        return CacheUtils.get(key);
    }

    @Override
    public void remove(String key) {
        CacheUtils.remove(key);
    }

    @Override
    public String[] getCacheNames() {
        return CacheUtils.getCacheNames();
    }

    @Override
    public List<String> getCacheKeys(String cacheName) {
        return CacheUtils.getCache(cacheName).getKeys();
    }

    @Override
    public Object getCacheValue(String cacheName, String cacheKey) {
        return CacheUtils.get(cacheName, cacheKey);
    }

    @Override
    public void clearCacheName(String cacheName) {
        CacheUtils.removeAll(cacheName);
    }

    @Override
    public void clearCacheKey(String cacheName, String cacheKey) {
        CacheUtils.remove(cacheName, cacheKey);
    }

    @Override
    public void clearAll() {
        String[] cacheNames = getCacheNames();
        for (String cacheName : cacheNames)
        {
            CacheUtils.removeAll(cacheName);
        }
    }
}
