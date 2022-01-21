package com.itxiaolin.rpc.serialization;

import com.caucho.hessian.io.HessianSerializerInput;
import com.caucho.hessian.io.HessianSerializerOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * hessian 实现序列化和反序列化
 */
public class HessianSerialization implements RpcSerialization{
    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        if (null == obj){
            throw new NullPointerException();
        }
        byte[] bytes;
        HessianSerializerOutput hessianOutput;
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            hessianOutput = new HessianSerializerOutput(os);
            hessianOutput.writeObject(obj);
            hessianOutput.flush();
            bytes = os.toByteArray();
        } catch (Exception e) {
            throw new SerializationException(e);
        }
        return bytes;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clz) throws IOException {

        if (data == null) {
            throw new NullPointerException();
        }
        T result;

        try (ByteArrayInputStream is = new ByteArrayInputStream(data)) {
            HessianSerializerInput hessianInput = new HessianSerializerInput(is);
            result = (T) hessianInput.readObject(clz);
        } catch (Exception e) {
            throw new SerializationException(e);
        }

        return result;
    }
}
