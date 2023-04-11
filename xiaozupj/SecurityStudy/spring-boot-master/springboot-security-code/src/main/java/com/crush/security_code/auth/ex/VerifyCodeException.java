package com.crush.security_code.auth.ex;

import javax.security.sasl.AuthenticationException;

/**
 * 声明一个验证码异常，用于抛出特定的验证码异常
 */
public class VerifyCodeException extends AuthenticationException {
    public VerifyCodeException(String msg) {
        super(msg);
    }
}
