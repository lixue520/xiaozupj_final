package com.hhz.controller;

import com.hhz.domain.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
//    @PreAuthorize("hasAuthority('system:dept:list')")
    @PreAuthorize("@ex.hasAuthority('sys:book:test')")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/testCors")
    public ResponseResult testCors() {
        return new ResponseResult(200, "testCors");
    }
}
