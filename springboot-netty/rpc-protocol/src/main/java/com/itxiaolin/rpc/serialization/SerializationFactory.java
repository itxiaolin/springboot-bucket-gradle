package com.itxiaolin.rpc.serialization;

/**
 * 序列化工厂
 */
public class SerializationFactory {

    /**
     * 获取序列化类
     * @param serializationType
     * @return
     */
    public static RpcSerialization getRpcSerialization(byte serializationType) {
        SerializationTypeEnum typeEnum = SerializationTypeEnum.findByType(serializationType);

        switch (typeEnum) {
            case HESSIAN:
                return new HessianSerialization();
            case JSON:
                return new JsonSerialization();
            default:
                throw new IllegalArgumentException("serialization type is illegal, " + serializationType);
        }
    }
}
