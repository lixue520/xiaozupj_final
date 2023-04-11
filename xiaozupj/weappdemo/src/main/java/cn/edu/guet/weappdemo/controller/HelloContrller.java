package cn.edu.guet.weappdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binqing
 * @类说明
 * @date 2022/8/12
 */
@RestController
public class HelloContrller {
    @GetMapping("/")
    public String hello() {
        return "这是jenkins管理v2.0";
    }

}
