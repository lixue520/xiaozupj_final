package com.xzb.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    // 是否有test权限
    // @PreAuthorize("hasAuthority('system:dept:list')")
    @PreAuthorize("@ex.hasAuthority('system:dept:list')")
    public String hello(){
        return "hello";
    }

    @GetMapping("king")
    public String j(){
        return "ssss";
    }
}
