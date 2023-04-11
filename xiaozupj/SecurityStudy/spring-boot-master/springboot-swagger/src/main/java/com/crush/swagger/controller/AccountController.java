package com.crush.swagger.controller;

import com.crush.swagger.entity.Account;
import com.crush.swagger.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: crush
 * @Date: 2021-07-29 17:32
 * version 1.0
 */
@Api(tags = "账户相关接口")
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ApiOperation("注册接口")
    @PostMapping("/register")
    public String register(@RequestBody @ApiParam(required = true, value = "注册账户需要的信息") Account account) {
        accountService.register(account);
        return "OK";
    }

    @ApiOperation("查询全部")
    @GetMapping
    public List<Account> select() {
        return accountService.select();
    }
}
