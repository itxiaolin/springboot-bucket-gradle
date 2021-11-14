package com.itxiaolin.ehcache.manage;

import com.itxiaolin.ehcache.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * 功能描述：确保应用退出时能关闭后台线程
 * @author lxq
 * @version 1.00
 * @Date 2020/12/8
 */
@Slf4j
@Component
public class ShutdownManager {
    private static CacheManager cacheManager = SpringUtil.getBean(CacheManager.class);
    @PreDestroy
    public void destroy()
    {
        shutdownEhCacheManager();
    }
    private void shutdownEhCacheManager()
    {
        try
        {
            log.info("====关闭缓存====");

            if (cacheManager != null)
            {
                cacheManager.shutdown();
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
    }
}
