package com.crush.security_code.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author lzw
 * @since 2021-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("account")
public class MyUser implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    private int id;

    private String username;

    private String password;

    // 1:启用 ， 0：禁用
    @TableField(exist = false)
    private Integer enabled = 1;

    // 1：锁住 ， 0：未锁
    @TableField(exist = false)
    private Integer locked = 0;

    private String role;

    @TableField(exist = false)
    private String token;


    /**
     * 验证码
     */
    @TableField(exist = false)
    private String verifyCode;

    /**
     * 验证码的uuid
     */
    @TableField(exist = false)
    private String uuid;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        authorities.add(authority);
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled == 1;
    }
}
