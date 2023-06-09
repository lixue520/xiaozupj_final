package com.crush.security_code;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: crush
 * @Date: 2021-09-06 12:42
 * version 1.0
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.crush.security_code.mapper")
public class SecurityCode {
    public static void main(String[] args) {
        SpringApplication.run(SecurityCode.class);
    }
}
