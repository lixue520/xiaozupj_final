package com.crush.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crush.mybatisplus.entity.TbUser;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author crush
 * @since 2021-07-23
 */
@Cacheable(cacheNames = "emp")
public interface ITbUserService extends IService<TbUser> {

    /**
     * 查询全部
     * @return
     */
    List<TbUser> getList();


    /**
     * 根据id查询
     * @param id
     * @return
     */
    TbUser getOneById(String id);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    List<TbUser> getByUsername(String username);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    String deleteById(String id);

    /**
     * 根据id更新名字
     * @param id
     * @param username
     * @return
     */
    TbUser updateById(String id, String username);
}
