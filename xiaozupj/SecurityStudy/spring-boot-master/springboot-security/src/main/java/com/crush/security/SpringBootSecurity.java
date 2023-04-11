package com.crush.security;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: crush
 * @Date: 2021-07-29 18:08
 * version 1.0
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.crush.security.mapper")
public class SpringBootSecurity {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurity.class);
    }
}
