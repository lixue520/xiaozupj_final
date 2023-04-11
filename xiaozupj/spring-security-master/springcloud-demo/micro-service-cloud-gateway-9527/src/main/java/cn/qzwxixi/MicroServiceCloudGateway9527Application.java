package cn.qzwxixi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //使用 @EnableEurekaClient 注解开启 Eureka 客户端功能，代码如下。
public class MicroServiceCloudGateway9527Application {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceCloudGateway9527Application.class, args);
    }
}
