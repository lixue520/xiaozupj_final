package cn.qzwxixi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //开启 Eureka 客户端功能
@EnableCircuitBreaker //激活熔断器功能
//通常情况下，我们都会在客户端进行服务降级，当客户端调用的服务端的服务不可用时，
// 客户端直接进行服务降级处理，避免其线程被长时间、不必要地占用。
public class MicroServiceCloudProviderDeptHystrix8004Application {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceCloudProviderDeptHystrix8004Application.class, args);
    }

}
