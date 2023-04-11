package com.crush.async.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: crush
 * @Date: 2021-07-29 16:35
 * version 1.0
 */
@Data
@Accessors(chain = true)
public class Account {
    private String username;
    private String password;
    private String email;
}
