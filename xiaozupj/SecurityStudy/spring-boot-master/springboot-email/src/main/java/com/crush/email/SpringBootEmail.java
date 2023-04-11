package com.crush.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: crush
 * @Date: 2021-07-23 23:12
 * version 1.0
 */
@SpringBootApplication
@EnableAsync
public class SpringBootEmail {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEmail.class);
    }
}
