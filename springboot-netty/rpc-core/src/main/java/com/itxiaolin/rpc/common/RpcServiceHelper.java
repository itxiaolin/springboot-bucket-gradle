package com.itxiaolin.rpc.common;

public class RpcServiceHelper {
    /**
     * 构建服务key
     * @param serviceName 服务名
     * @param serviceVersion 服务版本
     * @return 拼接key
     */
    public static String buildServiceKey(String serviceName, String serviceVersion) {
        return String.join("#", serviceName, serviceVersion);
    }
}
