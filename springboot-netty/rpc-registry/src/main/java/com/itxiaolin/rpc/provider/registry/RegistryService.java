package com.itxiaolin.rpc.provider.registry;

import com.itxiaolin.rpc.common.ServiceMeta;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import java.io.IOException;

public interface RegistryService {

    /**
     * 注册
     */
    void register(ServiceMeta serviceMeta) throws Exception;

    /**
     * 取消注册
     */
    void unRegister(ServiceMeta serviceMeta) throws Exception;

    /**
     * 发现
     */
    ServiceMeta discovery(String serviceName, int invokerHashCode) throws Exception;

    /**
     * 注销
     */
    void destroy() throws IOException;
}
