package com.crush.log_exception.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: crush
 * @Date: 2021-08-14 14:24
 * version 1.0
 */
@Data
@Accessors(chain = true)
public class LogEmail {
    private String toEmail;
    private String fromEmail;
    private String subject;
    private String context;
}
