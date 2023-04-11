package cn.qzwxixi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @version 1.0
 * @Author qin
 * @Date 2022/10/12 2:44
 * Nacos Server 还可以作为配置中心，对 Spring Cloud 应用的外部配置
 * 进行统一地集中化管理。而我们只需要在应用的 POM 文件中引入 spring-cloud-starter-alibaba-nacos-config
 * 即可实现配置的获取与动态刷新。
 * 从配置管理的角度看，Nacos 可以说是 Spring Cloud Config 的替代方案，
 * 但相比后者 Nacos 的使用更简单，操作步骤也更少。
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudAlibabaNacosConfigClient3377Application {
    
    public static void main(String[] args) {

        SpringApplication.run(SpringCloudAlibabaNacosConfigClient3377Application.class, args);
    }
}