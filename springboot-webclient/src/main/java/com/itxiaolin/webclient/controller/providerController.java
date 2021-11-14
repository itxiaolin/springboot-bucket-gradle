package com.itxiaolin.webclient.controller;

import com.itxiaolin.webclient.model.LoginParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author lxq
 * @version 1.0
 * @description: 模拟另一台服务器接口，可以通过线程名字不同判断 提供get 和post测试，put和delete同理，不测试
 * @date 2021/1/14 17:05
 */
@Slf4j
@RestController
public class providerController {
    @PostMapping("/login")
    @ResponseBody
    public void login(LoginParam loginParam) {
        log.debug("/login loginParam:{}",loginParam);
    }

    @GetMapping("hello/get")
    public String hello() throws InterruptedException {
        log.debug("hello");
        return "hello";
    }

    @GetMapping("hello/get/getByPathVariable/{id}")
    public String getByPathVariable(@PathVariable String id)  {
        log.debug("hello/{id}:-->{}",id);
        return id;
    }

    @GetMapping("hello/get/getByRequestParam")
    public String getByRequestParam(@RequestParam String username,@RequestParam String password)  {
        log.debug("\"hello/get/getByMultiValue:-->username: {},password:{}",username,password);
        return "username:"+username+",password:"+password;
    }
    @GetMapping("hello/get/getTimeOut")
    public String getTimeOut() throws InterruptedException {
        log.debug("hello/getTimeOut");
        Thread.sleep(3000L);
        return "hello/getTimeOut";
    }

    @PostMapping("hello/post/postByRequestParam")
    public String postByRequestParam(@RequestParam String username,@RequestParam String password)  {
        log.debug("\"hello/get/getByMultiValue:-->username: {},password:{}",username,password);
        return "username:"+username+",password:"+password;
    }

    @PostMapping("hello/post/postByRequestBody")
    public LoginParam postByRequestBody(@RequestBody LoginParam loginParam){
        log.debug("hello/post/postByRequestBody，获取请求体数据：{}",loginParam);
        return loginParam;
    }
}
