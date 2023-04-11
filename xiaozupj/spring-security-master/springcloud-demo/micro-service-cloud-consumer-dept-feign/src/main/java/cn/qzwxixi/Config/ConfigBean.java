package cn.qzwxixi.Config;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/10/11 21:04
 */
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ConfigBean {
    /**
     * OpenFeign 日志增强
     * 配置 OpenFeign 记录哪些内容
     */
    @Bean
    Logger.Level feginLoggerLevel() {
        /**
         * 该配置的作用是通过配置的 Logger.Level 对象告诉 OpenFeign 记录哪些日志内容。
         */
        return Logger.Level.FULL;
    }
}
