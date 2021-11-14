package com.itxiaolin.resilience.controller.ratelimiter;

import com.itxiaolin.resilience.domain.AjaxResult;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @className: BackendBController
 * @description: 模拟限流 B
 * @author: lxq
 * @date: 2021/8/16
 **/
@RestController
@RequestMapping(value = "/backendB")
public class BackendBController {

    /**
     * 无限制调用
     */
    @GetMapping("/rateLimiter/crud")
    public AjaxResult rateLimiterCURD(){
        return AjaxResult.success("请求成功");
    }

    /**
     * 限制调用，无默认返回
     */
    @GetMapping("/rateLimiter/cpuORio")
    @RateLimiter(name = "backendB" )
    public AjaxResult rateLimiterCpuORio(){
        return AjaxResult.success("请求成功");
    }

    /**
     * 限制调用，默认返回
     */
    @GetMapping("/rateLimiter/cpuORioDefault")
    @RateLimiter(name = "backendB", fallbackMethod  = "getDefault")
    public AjaxResult rateLimiterCpuORioDefault(){
        return AjaxResult.success("请求成功");
    }

    public AjaxResult getDefault(Throwable throwable){
        return AjaxResult.error("被限制请求了！");
    }
}