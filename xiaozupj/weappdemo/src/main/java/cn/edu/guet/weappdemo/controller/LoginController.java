package cn.edu.guet.weappdemo.controller;

import cn.edu.guet.weappdemo.bean.LoginBean;
import cn.edu.guet.weappdemo.bean.SysUser;
import cn.edu.guet.weappdemo.http.HttpResult;
import cn.edu.guet.weappdemo.security.JwtAuthenticationToken;
import cn.edu.guet.weappdemo.service.SysUserService;
import cn.edu.guet.weappdemo.util.PasswordUtils;
import cn.edu.guet.weappdemo.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
/*
如果没有使用RestController，那么SpringBoot和SpringMVC的方法，如果返回的是String类型，
说明方法执行完毕后，需要进行页面跳转
 */
//Controller
//RestController:说明springBoot的方法，将来返回Json格式，不再进行页面跳转
@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("login")//地址
//    public HttpResult login(String username, String password, HttpServletRequest request) {//表单形式
//    @RequestBody:标志接收Json格式数据
//    loginBean:用来接收存储Json格式数据
//    HttpResult：统一返回结果的封装
    public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        System.out.println(username);
        System.out.println(password);
        // 用户信息
        SysUser user = sysUserService.findByName(username);
        // 账号不存在、密码错误
        if (user == null) {
            return HttpResult.error("账号不存在");
        }
        if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
            return HttpResult.error("密码不正确");
        }
        // 账号锁定
        if (user.getStatus() == 0) {
            return HttpResult.error("账号已被锁定,请联系管理员");
        }
        // 系统登录认证
        JwtAuthenticationToken token = SecurityUtils.login(request, username, password, authenticationManager);
        System.out.println(token);
        return HttpResult.ok(token);
    }
}
