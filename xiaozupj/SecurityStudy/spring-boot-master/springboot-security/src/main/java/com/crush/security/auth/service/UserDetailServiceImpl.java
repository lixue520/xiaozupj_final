package com.crush.security.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crush.security.entity.MyUser;
import com.crush.security.service.IMyUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author crush
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    final
    IMyUserService userService;

    public UserDetailServiceImpl(IMyUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userService.getOne(new QueryWrapper<MyUser>().eq("username", username));
        return user;
    }
}
