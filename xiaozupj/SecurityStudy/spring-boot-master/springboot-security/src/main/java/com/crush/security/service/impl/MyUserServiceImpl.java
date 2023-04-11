package com.crush.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crush.security.entity.MyUser;
import com.crush.security.mapper.MyUserMapper;
import com.crush.security.service.IMyUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzw
 * @since 2021-05-12
 */
@Service
public class MyUserServiceImpl extends ServiceImpl<MyUserMapper, MyUser> implements IMyUserService {

}
