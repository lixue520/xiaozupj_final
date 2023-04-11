package cn.edu.guet.weappdemo.mapper;

import java.util.List;

import cn.edu.guet.weappdemo.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Liwei
 * @Date 2021-08-13 17:50
 */
@Mapper
public interface SysUserMapper {
    SysUser findByName(@Param(value = "name") String name);
    List<SysUser> getUser();

    void addUser(String name,String password,String nick_name,String salt);

    void addPower(String name);

    void updateUser(String id, String name, String password, String nick_name);

    void updateUser_nokey(String id, String name, String nick_name);
}