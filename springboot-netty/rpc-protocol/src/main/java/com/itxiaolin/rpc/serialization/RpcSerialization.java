package com.itxiaolin.rpc.serialization;

import java.io.IOException;

public interface RpcSerialization {
    /**
     * 序列化
     * @param obj 序列化参数
     * @param <T> 参数泛型
     * @return 字节数组
     * @throws IOException
     */
    <T> byte[] serialize(T obj) throws IOException;

    /**
     * 反序列化
     * @param data 字节数组
     * @param clz Class类型
     * @param <T> 泛型
     * @return 对象
     * @throws IOException
     */
    <T> T deserialize(byte[] data, Class<T> clz) throws IOException;
}
