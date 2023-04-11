package com.crush.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crush.mybatisplus.entity.TbUser;
import com.crush.mybatisplus.mapper.TbUserMapper;
import com.crush.mybatisplus.service.ITbUserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author crush
 * @since 2021-07-23
 */
//@CacheConfig(cacheNames = "tb_user")
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {


    private final TbUserMapper tbUserMapper;

    public TbUserServiceImpl(TbUserMapper tbUserMapper) {
        this.tbUserMapper = tbUserMapper;
    }


    @Cacheable(cacheNames = "tb_user")
    @Override
    public List<TbUser> getList() {
        return tbUserMapper.selectList(null);
    }


    @Cacheable(cacheNames = "tb_user" ,key="#id>0")
    @Override
    public TbUser getOneById(String id) {
        return tbUserMapper.selectById(id);
    }

    @Cacheable(cacheNames = "tb_user" ,key="#username")
    @Override
    public List<TbUser> getByUsername(String username) {
        return null;
    }

    @CacheEvict(cacheNames = "tb_user",key = "#id>0")
    @Override
    public String deleteById(String id) {
        return tbUserMapper.deleteById(id)>0?"OK":"NO";
    }

    @CachePut(cacheNames="tb_user",key = "#id>0")
    @Override
    public TbUser updateById(String id, String username) {
        TbUser tbUser = new TbUser().setId(id).setUsername(username);
        return tbUserMapper.updateById(tbUser)>0?tbUserMapper.selectById(id):null;
    }
}
