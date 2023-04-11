package com.crush.mybatisplus.service.impl;

import com.crush.mybatisplus.entity.User;
import com.crush.mybatisplus.mapper.UserMapper;
import com.crush.mybatisplus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author crush
 * @since 2021-07-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
