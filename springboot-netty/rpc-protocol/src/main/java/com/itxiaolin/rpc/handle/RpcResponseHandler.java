package com.itxiaolin.rpc.handle;

import com.itxiaolin.rpc.common.RpcFuture;
import com.itxiaolin.rpc.common.RpcRequestHolder;
import com.itxiaolin.rpc.common.RpcResponse;
import com.itxiaolin.rpc.prortocol.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RpcResponseHandler extends SimpleChannelInboundHandler<RpcProtocol<RpcResponse>> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcProtocol<RpcResponse> msg) throws Exception {
        long requestId = msg.getHeader().getRequestId();
        RpcFuture<RpcResponse> future = RpcRequestHolder.REQUEST_MAP.remove(requestId);
        future.getPromise().setSuccess(msg.getBody());
    }
}
