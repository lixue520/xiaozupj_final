package com.crush.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: crush
 * @Date: 2021-08-15 11:57
 * version 1.0
 */
@Data
@Accessors(chain = true)
public class Log {
    private Integer id;
    private String createTime;
    private String log;
}
