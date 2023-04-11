package com.crush.security_code.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author cuberxp
 * @date 2021/1/18 4:44 下午
 */
@Data
@Accessors(chain = true)
public class VerifyVO {
    private String img;
    private String uuid;
}
