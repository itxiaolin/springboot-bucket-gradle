package com.itxiaolin.webclient.controller;

import com.alibaba.fastjson.JSONObject;
import com.itxiaolin.webclient.model.LoginParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class PostController {

    /**
     * post 通过 MultiValue发送数据
     *
     * @return
     */
    @GetMapping("/postByMultiValue")
    public Mono<String> postByRequestParam() {
        String url = "localhost:8091/hello/post/postByRequestParam";
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("username", "lxq");
        multiValueMap.add("password", "123456");
        WebClient webClient = WebClient.builder().build();
        Mono<String> mono = webClient.post().uri(url).bodyValue(multiValueMap).retrieve().bodyToMono(String.class);
        return mono;
    }

    /**
     * post 通过 请求体发送数据
     *
     * @return
     */
    @GetMapping("/postByRequestBody")
    public Mono<LoginParam> postByRequestBody() {
        String url = "localhost:8091/hello/post/postByRequestBody";
        LoginParam loginParam = new LoginParam();
        loginParam.setUsername("lxq");
        loginParam.setPassword("123456");
        WebClient webClient = WebClient.builder().build();
        Mono<LoginParam> loginParamMono = webClient.post().uri(url).bodyValue(loginParam).retrieve().bodyToMono(LoginParam.class);
        return loginParamMono;
    }

    /**
     * post 通过 请求体发送Json数据
     *
     * @return
     */
    @GetMapping("/postByJson")
    public Mono<LoginParam> postByJson() {
        String url = "localhost:8091/hello/post/postByRequestBody";
        String json ="{\n" +
                "    \"username\": \"lxq\",\n" +
                "    \"password\": \"123456\"\n" +
                "}";
        WebClient webClient = WebClient.builder().build();
        Mono<LoginParam> loginParamMono = webClient.post().uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(json)).retrieve().bodyToMono(LoginParam.class);
        return loginParamMono;
    }

    /**
     * post 通过 请求体发送Json数据
     *
     * @return
     */
    @GetMapping("/postByJson2")
    public Mono<LoginParam> postByJson2() {
        String url = "localhost:8091/hello/post/postByRequestBody";
        String json ="{\n" +
                "    \"username\": \"lxq\",\n" +
                "    \"password\": \"123456\"\n" +
                "}";
        WebClient webClient = WebClient.builder().build();
        Mono<LoginParam> loginParamMono = webClient.post().uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(json),json.getClass()).retrieve().bodyToMono(LoginParam.class);
        return loginParamMono;
    }
    /**
     * post 通过 请求体发送Json数据
     *
     * @return
     */
    @GetMapping("/postByJson3")
    public Mono<LoginParam> postByJson3() {
        String url = "localhost:8091/hello/post/postByRequestBody";
        LoginParam loginParam = new LoginParam();
        loginParam.setUsername("lxq");
        loginParam.setPassword("123456");
        WebClient webClient = WebClient.builder().build();
        Mono<LoginParam> loginParamMono = webClient.post().uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(loginParam),loginParam.getClass()).retrieve().bodyToMono(LoginParam.class);
        return loginParamMono;
    }

    /**
     * post 通过 请求体发送Json数据
     *
     * @return
     */
    @GetMapping("/postByJson4")
    public Mono<LoginParam> postByJson4() {
        String url = "localhost:8091/hello/post/postByRequestBody";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","lxq");
        jsonObject.put("password","123456");
        WebClient webClient = WebClient.builder().build();
        Mono<LoginParam> loginParamMono = webClient.post().uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(jsonObject),jsonObject.getClass()).retrieve().bodyToMono(LoginParam.class);
        return loginParamMono;
    }

    @GetMapping("/doPostByfFile")
    public Mono<String> doPostByfFile( MultipartFile multipartFile) {
        String url = "localhost:8091/hello/post/doPostByfFile";
        log.debug("请求接口{}",url);
        WebClient webClient = WebClient.builder().build();
        File file = multipartFile2File(multipartFile);
        FileSystemResource resource = new FileSystemResource(file);
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add(multipartFile.getOriginalFilename(), resource);
        return webClient.post()
                .uri(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(param))//构建表单数据
                .retrieve()// 获取响应体
                .bodyToMono(String.class)
                //出现问题默认返回值
                .onErrorReturn("");
    }

    private File multipartFile2File(MultipartFile file)  {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        // 用uuid作为文件名，防止生成的临时文件重复
        File tempFile =null;
        try {
            tempFile = File.createTempFile(UUID.randomUUID().toString(), prefix);
            file.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return tempFile;
    }
}
