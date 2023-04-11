package cn.edu.guet.weappdemo.security;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cn.edu.guet.weappdemo.bean.SysUser;
import cn.edu.guet.weappdemo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * 用户登录认证信息查询，从数据库去加载被认证的用户信息
 * Service指定了该Bean在IoC容器管理的名字：UserDetailsServiceImpl
 * @Author Liwei
 * @Date 2021-08-13 17:52
 */
@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        Set<String> permissions = sysUserService.findPermissions(user.getName());
        System.out.println("获取权限成功---------用于验证身份");
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        //把用户信息，名字，密码，盐，权限存到JwtUserDetails
        return new JwtUserDetails(user.getName(), user.getPassword(), user.getSalt(), grantedAuthorities);
    }
}