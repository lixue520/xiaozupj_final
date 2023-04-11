package com.crush.security_code.auth.ex;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 登陆相关的枚举
 * @author cuberxp
 * @since 1.0.0
 * Create time 2019/12/20 19:47
 */
@Getter
@AllArgsConstructor
public enum AuthorityResponseEnum   implements IResponseEnum{
    /**
     * 验证码相关的枚举
     */
    LOGIN_IS_SUCCESS(200, "登陆成功!!!"),
    KEY_NOT_EXISTS(401, "该key不存在"),
    VERIFY_CODE_TIMEOUT(5005,"验证码己过期了哦!"),
    VERIFY_CODE_NOT_EQUALS(5005,"验证码不一至哦!"),
    VERIFY_CODE_INVALID_ERROR(5005, "验证码己失效!"),
    LOGIN_COUNT_ERROR(5004, "登陆次数过多啦！五分钟后再登陆!"),
    PASSWORD_OLD_ERROR(5006, "原密码错误!"),
    USERNAME_OR_PASSWORD_ERROR(5006,"用户名或密码错误! 你不会是乱输的吧。。。"),
    PASSWORD_ERROR(5006,"密码不能为空且长度6~24!"),
    USERNAME_ERROR(5007, "账户不能为空且长度6~24!"),
    USER_INNER_IP_ERROR(5009,"请在内网登陆该账户!"),
    USER_IS_FREEZE(5010, "账户己被冻结"),
    EXPIRED_JWT(5011, "账户己过期!请重新登陆!"),
    GITHUB_NOT_BINDING(5012, "该github账户没有绑定你的账户"),
    EMAIL_ERROR(5013, "邮箱格式不对"),
    OAUTH_ID_EXISTS(5014, "该第三方平台账户己绑定另一个账户了!"),
    OLD_PASSWORD_ERROR(5015,"旧密码错误! 你不会是乱输的吧。。。"),
    ;


    private final int code;
    private final String message;
}
