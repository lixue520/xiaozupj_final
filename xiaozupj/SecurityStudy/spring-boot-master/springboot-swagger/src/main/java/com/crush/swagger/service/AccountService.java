package com.crush.swagger.service;

import com.crush.swagger.entity.Account;

import java.util.List;

/**
 * @Author: crush
 * @Date: 2021-07-29 17:31
 * version 1.0
 */

public interface AccountService {

    /**
     * 注册
     * @param account
     * @return
     */
    boolean register(Account account);

    /**
     * 查询全部
     */
    List<Account> select();

}
