package com.itxiaolin.resilience;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Resilience4jApplication {
    public static void main(String[] args) {
        SpringApplication.run(Resilience4jApplication.class, args);
    }
}
