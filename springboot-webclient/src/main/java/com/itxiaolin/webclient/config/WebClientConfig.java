package com.itxiaolin.webclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorResourceFactory;


@Slf4j
@Configuration
public class WebClientConfig {


    /**
     * 但是，如果服务器可以启动或停止进程内(例如，作为 WAR 部署的 Spring MVC 应用程序)，
     * 则可以声明ReactorResourceFactory和globalResources=true(默认值)类型的 Spring 托管 bean，
     * 以确保 Reactor Netty 全局 SpringApplicationContext关闭时，资源将关闭
     * @return ReactorResourceFactory
     */
    @Bean
    public ReactorResourceFactory reactorResourceFactory() {
        return new ReactorResourceFactory();
    }
}
