package com.itxiaolin.ehcache.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Slf4j
public class CacheUtils
{

    private static CacheManager cacheManager = SpringUtil.getBean(CacheManager.class);

    private static final String SYS_CACHE = "demo-cache";

    /**
     * 获取demo-cache缓存
     * 
     * @param key
     * @return
     */
    public static Object get(String key)
    {
        return get(SYS_CACHE, key);
    }

    /**
     * 获取demo-cache缓存
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(String key, Object defaultValue)
    {
        Object value = get(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 写入demo-cache缓存
     * 
     * @param key
     * @return
     */
    public static void put(String key, Object value)
    {
        put(SYS_CACHE, key, value);
    }

    /**
     * 从demo-cache缓存中移除
     * 
     * @param key
     * @return
     */
    public static void remove(String key)
    {
        remove(SYS_CACHE, key);
    }

    /**
     * 获取缓存
     *
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key)
    {
        return getCache(cacheName).get(getKey(key));
    }

    /**
     * 获取缓存
     * 
     * @param cacheName
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(String cacheName, String key, Object defaultValue)
    {
        Object value = get(cacheName, getKey(key));
        return value != null ? value : defaultValue;
    }

    /**
     * 写入缓存
     * 
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value)
    {
       getCache(cacheName).put(new Element(key, value));
    }

    /**
     * 从缓存中移除
     *
     * @param cacheName
     * @param key
     */
    public static void remove(String cacheName, String key)
    {
        getCache(cacheName).remove(getKey(key));
    }

    /**
     * 从缓存中移除所有
     * 
     * @param cacheName
     */
    public static void removeAll(String cacheName)
    {
        Cache cache = getCache(cacheName);
        List<String> keys = cache.getKeys();
        for (String key : keys) {
            cache.remove(key);
        }
        log.info("清理缓存： {} => {}", cacheName, keys);
    }

    /**
     * 从缓存中移除指定key
     * 
     * @param keys
     */
    public static void removeByKeys(Set<String> keys)
    {
        removeByKeys(SYS_CACHE, keys);
    }

    /**
     * 从缓存中移除指定key
     * 
     * @param cacheName
     * @param keys
     */
    public static void removeByKeys(String cacheName, Set<String> keys)
    {
        for (Iterator<String> it = keys.iterator(); it.hasNext();)
        {
            remove(it.next());
        }
        log.info("清理缓存： {} => {}", cacheName, keys);
    }

    /**
     * 获取缓存键名
     * 
     * @param key
     * @return
     */
    private static String getKey(String key)
    {
        return key;
    }

    /**
     * 获得一个Cache，没有则显示日志。
     * 
     * @param cacheName
     * @return
     */
    public static Cache getCache(String cacheName)
    {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null)
        {
            throw new RuntimeException("当前系统中没有定义“" + cacheName + "”这个缓存。");
        }
        return cache;
    }

    /**
     * 获取所有缓存
     *
     * @return 缓存组
     */
    public static String[] getCacheNames()
    {
        return   cacheManager.getCacheNames();
    }
}
