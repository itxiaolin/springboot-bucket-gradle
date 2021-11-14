package com.itxiaolin.webclient.controller;

import com.itxiaolin.webclient.model.LoginParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
public class ExchangeController {

    /**
     * exchange post通过 MultiValue发送数据
     * @return
     */
    @GetMapping("/exchangeByMultiValue")
    public Mono<String> postByRequestParam(){
        String url="http://localhost:8091/hello/post/postByRequestParam";
        MultiValueMap<String,String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("username","lxq");
        multiValueMap.add("password","123456");
        WebClient webClient = WebClient.builder().build();
        Mono<String> mono = webClient.post().uri(url).bodyValue(multiValueMap).exchange().flatMap(clientResponse ->{
             log.debug("测试exchange");
             return clientResponse.bodyToMono(String.class);
       });
        return mono;
    }

    /**
     * exchange post通过请求体发送数据
     * @return
     */
    @GetMapping("/exchangeByRequestBody")
    public Mono<LoginParam> postByRequestBody(){
        String url="http://localhost:8091/hello/post/postByRequestBody";
        LoginParam loginParam = new LoginParam();
        loginParam.setUsername("lxq");
        loginParam.setPassword("123456");
        WebClient webClient = WebClient.builder().build();
        Mono <LoginParam> loginParamMono = webClient.post().uri(url).bodyValue(loginParam).exchange().flatMap(clientResponse ->clientResponse.bodyToMono(LoginParam.class));
        return loginParamMono;
    }
}
