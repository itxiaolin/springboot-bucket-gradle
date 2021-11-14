package com.itxiaolin.webclient.controller;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author lxq
 * @version 1.0
 * @description: 通过 restTemplate 发送get请求
 * @date 2021/1/14 15:38
 */
@Slf4j
@RestController
public class GetController {
    /**
     * get不带参数
     * @return
     */
    @GetMapping("/get")
    public Flux<String> get(){
        String url="localhost:8091/hello/get";
        WebClient webClient = WebClient.create();
        Flux<String> flux = webClient.get().uri(url).retrieve().bodyToFlux(String.class);
        flux.subscribe(System.err::println);
        return flux;
    }

    /**
     * get通过 PathVariable 发送数据
     * @return
     */
    @GetMapping("/getByPathVariable")
    public Flux<String> getByPathVariable(){
        String id= "1";
        String url="localhost:8091/hello/get/getByPathVariable/{id}";
        WebClient webClient = WebClient.builder().build();
        Flux<String> flux = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(url).build(id))
                .retrieve()
                .bodyToFlux(String.class);
        return flux;
    }

    /**
     * get 通过 RequestParam 发送数据
     * @return
     */
    @GetMapping("/getByRequestParam")
    public Flux<String>   getByRequestParam(){
        String url="localhost:8091/hello/get/getByRequestParam";
        WebClient webClient = WebClient.builder().build();
        Flux<String> flux = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(url)
                        .queryParam("username","lxq")
                        .queryParam("password","123456")
                        .build())
                .retrieve()
                .bodyToFlux(String.class);
        return  flux;
    }


    /**
     * 超时异常处理
     * @return
     */
    @GetMapping("/getTimeOut")
    public Flux<String> getTimeOut(){
        String url="localhost:8091/hello/get/getTimeOut";
        log.debug("通过TcpClient配置超时时间");
        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,3000)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(3000, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(3000, TimeUnit.MILLISECONDS));
                });
        //替换方案
//        HttpClient httpClient = HttpClient.create()
//                .tcpConfiguration(client ->
//                        client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,3000)
//                                .doOnConnected(conn -> conn
//                                        .addHandlerLast(new ReadTimeoutHandler(3000, TimeUnit.MILLISECONDS))
//                                        .addHandlerLast(new WriteTimeoutHandler(3000, TimeUnit.MILLISECONDS))));
        WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        Flux<String> flux;
        flux = webClient.get().uri(url)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("4xx error")))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("5xx error")))
                .bodyToFlux(String.class)
                .doOnError(throwable -> log.info("fileDataStream  onError", throwable))
                //出现问题默认返回值
                .onErrorReturn("请求发送异常，请检查");
        return flux;
    }
}
