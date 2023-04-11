package com.crush.security_code.auth.ex;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

/**
 * 登陆相关的异常
 * @author cuberxp
 * @since 1.0.0
 * Create time 2020/1/31 16:41
 */
@Getter
public class LoginException extends AuthenticationException {
    private static final long serialVersionUID = -2191468967131214169L;

    private final IResponseEnum iResponseEnum;

    public LoginException(IResponseEnum iResponseEnum) {
        super(iResponseEnum.getMessage());
        this.iResponseEnum = iResponseEnum;
    }

}
