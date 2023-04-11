package com.crush.log.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crush.log.entity.LogUser;
import com.crush.log.mapper.LogUserMapper;
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
public class LogUserServiceImpl extends ServiceImpl<LogUserMapper, LogUser> implements ILogUserService {

}
