package cn.edu.guet.weappdemo.controller;

import cn.edu.guet.weappdemo.bean.SysUser;
import cn.edu.guet.weappdemo.http.HttpResult;
import cn.edu.guet.weappdemo.service.SysUserService;
import cn.edu.guet.weappdemo.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.Set;

/**
 * 用户控制器
 *
 * @Author Liwei
 * @Date 2021-08-17 07:15
 */
@RestController
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping(value = "/findPermissions")
    public HttpResult findPermissions(@RequestParam String name) {
        System.out.println("查找权限："+name);
        return HttpResult.ok(sysUserService.findPermissions(name));
    }
    @GetMapping(value = "/getUser")
    public HttpResult getUser(){
        System.out.println("获取用户");
        return  HttpResult.ok(sysUserService.getUser());
    }
    @GetMapping(value = "/addUser")
    public HttpResult addUser(@RequestParam String name,String password,String nick_name,String salt){
        System.out.println("添加用户");
        salt="b3f2ded289524fe98b12";
        PasswordEncoder encoderMd5 = new PasswordEncoder(salt, "MD5");

        String encode = encoderMd5.encode(password);
        return HttpResult.ok(sysUserService.addUser(name,encode,nick_name,salt));
    }
    @GetMapping(value = "/updateUser")
    public HttpResult updateUser(@RequestParam String id,String name,String password,String nick_name){
//        if(!password.equals(null))
////        {
////            System.out.println("有密码改动更新");
////            PasswordEncoder encoderMd5 = new PasswordEncoder("b3f2ded289524fe98b12", "MD5");
////            password= encoderMd5.encode(password);
////            return HttpResult.ok(sysUserService.updateUser(id,name,password,nick_name));
////        }
        System.out.println("无密码改动更新");
        return HttpResult.ok(sysUserService.updateUser_nokey(id,name,nick_name));
    }
}


