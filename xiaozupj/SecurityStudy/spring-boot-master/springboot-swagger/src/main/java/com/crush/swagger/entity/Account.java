package com.crush.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: crush
 * @Date: 2021-07-29 17:29
 * version 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "账户相关信息类")
public class Account {

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("密码")
    private String password;
}
