package com.crush.log.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: crush
 * @Date: 2021-08-14 8:43
 * version 1.0
 */
@Data
@Accessors(chain = true)
@TableName("tb_user")
public class LogUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String passwrod;

    /**
     * 逻辑删除字段
     */
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
