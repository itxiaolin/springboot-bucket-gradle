package com.itxiaolin.rpc.common;

import lombok.Data;

@Data
public class ServiceMeta {

    /**
     * 服务名
     */
    private String serviceName;

    /**
     * 服务版本
     */
    private String serviceVersion;

    /**
     * 服务地址
     */
    private String serviceAddr;

    /**
     * 服务端口
     */
    private int servicePort;
}
