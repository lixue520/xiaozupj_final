package com.crush.scheduled.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: crush
 * @Date: 2021-07-29 15:35
 * version 1.0
 */
@Data
@Accessors(chain = true)
public class Task {

    /**
     * 动态任务名曾
     */
    private String name;

    /**
     * 设定动态任务开始时间
     */
    private LocalDateTime start;
}
