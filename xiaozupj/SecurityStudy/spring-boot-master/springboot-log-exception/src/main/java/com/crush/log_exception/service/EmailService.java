package com.crush.log_exception.service;


import com.crush.log_exception.entity.LogEmail;

/**
 * @Author: crush
 * @Date: 2021-07-29 16:37
 * version 1.0
 */
public interface EmailService {


    /**
     * 异常发送短信
     */
    void senderEmail(LogEmail logEmail);
}
