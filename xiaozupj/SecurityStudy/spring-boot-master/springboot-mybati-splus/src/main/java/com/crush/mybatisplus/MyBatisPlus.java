package com.crush.mybatisplus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: crush
 * @Date: 2021-07-23 14:11
 * version 1.0
 */
@Slf4j
@SpringBootApplication
public class MyBatisPlus {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlus.class);
        log.info("druid 监控页面：localhost:8081/druid");
    }

}
