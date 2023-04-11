package com.crush.async.controller;

import com.crush.async.entity.Account;
import com.crush.async.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: crush
 * @Date: 2021-07-27 21:13
 * version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {

    private static List<Account> accountList=new ArrayList<Account>();

    @Autowired
    private  TaskExecutor taskExecutor;

    @Autowired
    private  EmailService emailService;

    /**
     * 写一个注册发送邮件的栗子
     */
    @PostMapping("/register")
    public String register(@RequestBody  Account account){
        accountList.add(account);
        emailService.senderEmail(account);
        log.info(Thread.currentThread().getName());
        return "OK";
    }

}
