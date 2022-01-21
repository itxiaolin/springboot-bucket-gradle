package com.itxiaolin.rpc.handle;

import com.itxiaolin.rpc.common.RpcRequest;
import com.itxiaolin.rpc.common.RpcResponse;
import com.itxiaolin.rpc.common.RpcServiceHelper;
import com.itxiaolin.rpc.prortocol.MsgHeader;
import com.itxiaolin.rpc.prortocol.MsgStatus;
import com.itxiaolin.rpc.prortocol.MsgType;
import com.itxiaolin.rpc.prortocol.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.reflect.FastClass;

import java.util.Map;

@Slf4j
public class RpcRequestHandler extends SimpleChannelInboundHandler<RpcProtocol<RpcRequest>> {
    private final Map<String, Object> rpcServiceMap;

    public RpcRequestHandler(Map<String, Object> rpcServiceMap) {
        this.rpcServiceMap = rpcServiceMap;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcProtocol<RpcRequest> msg) throws Exception {
        RpcRequestProcessor.submitRequest(()->{
            RpcProtocol<RpcResponse> resRpcProtocol = new RpcProtocol<>();
            RpcResponse rpcResponse = new RpcResponse();
            MsgHeader msgHeader = new MsgHeader();
            msgHeader.setMsgType((byte) MsgType.REQUEST.getType());
            try{
                Object result = handle(msg.getBody());
                rpcResponse.setData(result);
                //设置状态
                msgHeader.setStatus((byte) MsgStatus.SUCCESS.getCode());
                //设置返回值
                resRpcProtocol.setBody(rpcResponse);
            }catch (Throwable throwable){
                msgHeader.setStatus((byte) MsgStatus.FAIL.getCode());
                rpcResponse.setMessage(throwable.toString());
                log.error("process request {} error", msgHeader.getRequestId(), throwable);
            }
            //会将数据写到ChannelPipeline中当前ChannelHandler的下一个ChannelHandler开始处理。
            ctx.writeAndFlush(resRpcProtocol);
        });
    }

    private Object handle(RpcRequest request) throws Throwable {
        //构建服务key
        String serviceKey = RpcServiceHelper.buildServiceKey(request.getClassName(), request.getServiceVersion());
        //获取相对应的bean
        Object serviceBean = rpcServiceMap.get(serviceKey);

        if (null == serviceBean) {
            throw new RuntimeException(String.format("service not exist: %s:%s", request.getClassName(), request.getMethodName()));
        }
        //反射获取相对于class信息
        Class<?> serviceClass = serviceBean.getClass();
        //获取请求方法名
        String methodName = request.getMethodName();
        //请求参数类型
        Class<?>[] parameterTypes = request.getParameterTypes();
        //请求参数
        Object[] parameters = request.getParams();
        //FastClass动态子类实例
        FastClass fastClass = FastClass.create(serviceClass);
        //或许方法下标
        int methodIndex = fastClass.getIndex(methodName, parameterTypes);
        // 调用委托类方法
        return fastClass.invoke(methodIndex, serviceBean, parameters);
    }
}
