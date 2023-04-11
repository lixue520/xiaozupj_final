package com.crush.exception.controller;

import com.crush.exception.commons.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: crush
 * @Date: 2021-08-14 11:09
 * version 1.0
 */
@RestController
public class DemoController {


    @GetMapping("/test")
    public ResponseDto test(){
        return ResponseDto.success("我喜欢你！！！");
    }

}
