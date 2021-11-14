package com.itxiaolin.ehcache.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述：缓存操作处理
 * @author lxq
 * @version 1.00
 * @Date 2020/12/10
 */
@Service
public interface CacheService {

    public void put(String key, Object value);
    public Object get(String key);
    public void remove(String key);
    /**
     * 获取所有缓存名称
     *
     * @return 缓存列表
     */
    public String[] getCacheNames();
    /**
     * 根据缓存名称获取所有键名
     *
     * @param cacheName 缓存名称
     * @return 键名列表
     */
    public List<String> getCacheKeys(String cacheName);
    /**
     * 根据缓存名称和键名获取内容值
     *
     * @param cacheName 缓存名称
     * @param cacheKey 键名
     * @return 键值
     */
    public Object getCacheValue(String cacheName, String cacheKey);
    /**
     * 根据名称删除缓存信息
     *
     * @param cacheName 缓存名称
     */
    public void clearCacheName(String cacheName);

    /**
     * 根据名称和键名删除缓存信息
     *
     * @param cacheName 缓存名称
     * @param cacheKey 键名
     */
    public void clearCacheKey(String cacheName, String cacheKey);

    /**
     * 清理所有缓存
     */
    public void clearAll();
}
