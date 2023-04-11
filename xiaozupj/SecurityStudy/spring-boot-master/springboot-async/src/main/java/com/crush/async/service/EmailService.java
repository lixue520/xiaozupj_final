package com.crush.async.service;

import com.crush.async.entity.Account;

/**
 * @Author: crush
 * @Date: 2021-07-29 16:37
 * version 1.0
 */
public interface EmailService {


    /**
     * 用于注册成功后发送邮件
     * @param account 账号信息
     */
    void senderEmail(Account account);
}
