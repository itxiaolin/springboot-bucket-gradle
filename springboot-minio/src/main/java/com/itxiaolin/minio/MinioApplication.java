package com.itxiaolin.minio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author lxq
 * @version 1.0
 * @description: minio启动类
 * @date 2021/3/1 0:51
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MinioApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(MinioApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  minio模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
