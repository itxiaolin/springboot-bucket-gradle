package com.itxiaolin.rpc.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcRequest implements Serializable {
    /**
     * 服务版本
     */
    private String serviceVersion;

    /**
     * class名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数
     */
    private Object[] params;

    /**
     * 参数类型
     */
    private Class<?>[] parameterTypes;
}