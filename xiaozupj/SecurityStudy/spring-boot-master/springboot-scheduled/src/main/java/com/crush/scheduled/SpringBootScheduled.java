package com.crush.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @EnableScheduling 开启定时任务
 * @Author: crush
 * @Date: 2021-07-27 21:10
 * version 1.0
 */
@EnableScheduling
@SpringBootApplication
public class SpringBootScheduled {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootScheduled.class);
    }
}
