package com.itxiaolin.rpc.handle;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RpcRequestProcessor {
    private static ThreadPoolExecutor threadPoolExecutor;

    /**
     * 单例模式，双重检验，如果不存在就初始化线程池
     * @param task
     */
    public static void submitRequest(Runnable task) {
        if (threadPoolExecutor == null) {
            synchronized (RpcRequestProcessor.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000));
                }
            }
        }
        threadPoolExecutor.submit(task);
    }
}
