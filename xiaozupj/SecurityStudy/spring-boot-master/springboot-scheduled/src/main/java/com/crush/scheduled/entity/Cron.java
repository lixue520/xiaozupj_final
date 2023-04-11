package com.crush.scheduled.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Repository;

/**
 * @Author: crush
 * @Date: 2021-09-29 10:16
 * version 1.0
 */

@Data
@TableName("tb_cron")
public class Cron {

    private Long id;
    private String cronExpression;
    private String cronDescribe;
}
