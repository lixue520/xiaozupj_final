package com.crush.log_exception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: crush
 * @Date: 2021-08-14 14:37
 * version 1.0
 */

@SpringBootApplication
//@ComponentScan(basePackages={"com.crush.log_exception.mapper"})
public class SpringBootLogException {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootLogException.class);
    }
}
