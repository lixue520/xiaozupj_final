package com.crush.security_code.service;

import java.awt.*;

/**
 * 生成验证码图片
 * @author cuberxp
 * @since 1.0.0
 * Create time 2019/12/20 16:58
 */
public interface IGenerateImageService {

    /**
     * 根据给定的字符串生成验证码图片
     * @param verifyCode 验证码字符串
     * @return 验证码图片
     */
    Image imageWithDisturb(String verifyCode);

}