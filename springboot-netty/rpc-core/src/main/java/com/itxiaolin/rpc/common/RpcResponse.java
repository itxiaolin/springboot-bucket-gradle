package com.itxiaolin.rpc.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcResponse implements Serializable {
    //返回数据
    private Object data;
    //返回消息
    private String message;
}
