package com.crush.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: crush
 * @Date: 2021-07-27 21:10
 * version 1.0
 */
@Slf4j
@SpringBootApplication
public class SpringBootSwagger {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSwagger.class);
        log.info("API接口访问链接:http://localhost:8088/swagger-ui.html");
    }
}
