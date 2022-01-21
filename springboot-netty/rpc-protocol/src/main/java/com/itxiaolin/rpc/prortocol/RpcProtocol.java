package com.itxiaolin.rpc.prortocol;

import lombok.Data;

import java.io.Serializable;
@Data
public class RpcProtocol <T> implements Serializable {
    /**
     * 消息头
     */
    private MsgHeader header;
    /**
     * 消息体
     */
    private T body;
}
