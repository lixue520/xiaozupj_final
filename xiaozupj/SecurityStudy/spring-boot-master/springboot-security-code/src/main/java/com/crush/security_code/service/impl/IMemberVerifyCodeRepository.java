package com.crush.security_code.service.impl;

import cn.hutool.core.lang.Assert;
import com.crush.security_code.auth.ex.AuthorityResponseEnum;
import com.crush.security_code.auth.ex.LoginException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author cuberxp
 * @since 1.0.0
 * Create time 2019/12/20 17:07
 */
@Repository
public class IMemberVerifyCodeRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    public IMemberVerifyCodeRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String get(String key) {
        if(hasKey(key)) {
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

            return (String) valueOperations.get(key);
        }

        throw new LoginException(AuthorityResponseEnum.VERIFY_CODE_INVALID_ERROR);
    }

    public void delete(String key) {
        if(hasKey(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 保存到redis 中
     * 码证码有效期 20分钟
     * @param key session id
     * @param verifyCodeDigit 码证码
     */
    public void save(String key, String verifyCodeDigit) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, verifyCodeDigit, 20L, TimeUnit.MINUTES);
    }

    public boolean hasKey(String key) {
        Assert.notBlank(key);
        return Optional.ofNullable(redisTemplate.hasKey(key)).orElse(false);
    }
}
