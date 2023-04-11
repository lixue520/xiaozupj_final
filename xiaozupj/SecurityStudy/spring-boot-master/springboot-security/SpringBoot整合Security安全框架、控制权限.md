> 我想每个写项目的人，都肯定会遇到控制权限这个问题.
> 例如这个这个链接只能管理员访问，那个链接丫只能超级管理员访问等等，实现方式也有多种多样，`控制的粒度也不一样`。
> 以前刚学的时候，不会框架，大都是`手写注解+过滤器`来进行权限的控制，但这样增加了过滤器的负担。用起来也会稍微有些麻烦，粒度不太好控制。
>
> 用框架的话，就是封装了更多的操作，让一切更简单吧。当然不局限于Security，还有像Shiro安全框架，这两种非常常见。
> `一起加油吧！！！`😁
先看个图舒缓一下，准备开始吧🐱‍🏍

![在这里插入图片描述](https://img-blog.csdnimg.cn/0c70770964b043edac14cf47c9f9305e.JPG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


下面就开始吧！！！👇

@[TOC](SpringBoot整合Security安全框架、控制权限)

## 一、前言

### 介绍：

Spring Security是一个能够为基于Spring的企业应用系统提供声明式的安全访问控制解决方案的安全框架。它提供了一组可以在Spring应用上下文中配置的Bean，充分利用了Spring IoC，DI（控制反转Inversion of Control ,DI:Dependency Injection 依赖注入）和AOP（面向切面编程）功能，为应用系统提供声明式的安全访问控制功能，减少了为企业系统安全控制编写大量重复代码的工作。

### 官网：

[SpringSecurity 最新](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)

[SpringSecurity 5.0.6版本](https://docs.spring.io/spring-security/site/docs/5.0.6.RELEASE/reference/htmlsingle/#authz-role-voter)

### 优缺点：

优点

- Spring Boot 官方提供了大量的非常方便的开箱即用的 Starter ，包括 Spring Security 的 Starter ，使得在 Spring Boot 中使用 Spring Security 变得更加容易。

- Spring Security功能强大，比较好用。

缺点

1. Spring Security 是一个重量级的安全管理框架，
2. Spring Security概念复杂，配置繁琐（这个确实，没法逃开）

### 案例：

我们在访问一个网站时，大都都会设置普通用户能有的权限，然后管理员有的权限，再就是超级管理员等等，这次就是实现这样一个案例。

**项目结构：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/7d2af0b240de4d3cbbd03acef1405ce0.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


## 二、环境准备

### 2.1、数据库表

```sql
CREATE TABLE `account`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `account` VALUES (1, 'user', '$2a$10$1MHNdZS.oCICxLRVbnNBZe4CRn9Rk1MVQhasSMhHr0G4BCNQjPpna', 'ROLE_USER');
INSERT INTO `account` VALUES (2, 'admin', '$2a$10$dKkrkgVzaCPX74TvxOjwNuFJjIRJeAuDPKFntwNwRvRHkwIAHV5Q6', 'ROLE_ADMIN');
INSERT INTO `account` VALUES (3, 'super_admin', '$2a$10$CqOXnSp6oks9UTvsops4U.0vMGbUE2Bp28xKaPmlug4W8Mk59Sj8y', 'ROLE_SUPER_ADMIN');
INSERT INTO `account` VALUES (4, 'test', '$2a$10$SQsuH1XfxHdsVmf2nE75wOAE6GHm1nd/xDp/08KYJmtbzJt2J6xIG', 'TEST');

```

### 2.2、导入依赖

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.2</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>   
<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.4.1</version>
    </dependency>

    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.9.0</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <!--java版本太高  向下兼容的包-->
    <dependency>
        <groupId>javax.xml.bind</groupId>
        <artifactId>jaxb-api</artifactId>
        <version>2.3.0</version>
    </dependency>
</dependencies>
```

### 2.3、配置文件

```properties
# 应用名称
spring.application.name=demo
# 应用服务 WEB 访问端口
server.port=8080

spring.datasource.name=defaultDataSource
# 数据库连接地址
spring.datasource.url=jdbc:mysql://localhost:3306/security?serverTimezone=UTC
# 数据库用户名&密码：
spring.datasource.username=root
spring.datasource.password=123456

mybatis-plus.mapper-locations=classpath:mapper/**/*.xml

logging.level.com.crush.security.mapper=DEBUG

# token 存活时间
token.expire=3600000
token.key=123456

```

### 2.4、`WebSecurityConfig` Security的主要配置类： 



```java
import com.crush.security.auth.filter.JwtAuthenticationFilter;
import com.crush.security.auth.filter.JwtAuthorizationFilter;
import com.crush.security.auth.handle.MacLoginUrlAuthenticationEntryPoint;
import com.crush.security.auth.handle.MyAccessDeniedHandler;
import com.crush.security.auth.handle.MyLogoutSuccessHandler;
import com.crush.security.auth.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author crush
 */
@Configuration
@EnableWebSecurity
//启用全局配置
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**放行的路径*/
    private final String[] PATH_RELEASE = {
            "/login",
            "/all"
    };
    /***根据用户名找到用户*/
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private MacLoginUrlAuthenticationEntryPoint macLoginUrlAuthenticationEntryPoint;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    
      @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                /**antMatchers (这里的路径)   permitAll 这里是允许所有人 访问*/
                .antMatchers(PATH_RELEASE).permitAll()
                /** 映射任何请求 */
                .anyRequest()

                /** 指定任何经过身份验证的用户都允许使用URL。*/
                .authenticated()

                /** 指定支持基于表单的身份验证 */
                .and().formLogin().permitAll()

                /** 允许配置异常处理。可以自己传值进去 使用WebSecurityConfigurerAdapter时，将自动应用此WebSecurityConfigurerAdapter 。*/
                .and().exceptionHandling()

                /** 设置要使用的AuthenticationEntryPoint。   macLoginUrlAuthenticationEntryPoint   验证是否登录*/
                .authenticationEntryPoint(macLoginUrlAuthenticationEntryPoint)

                /** 指定要使用的AccessDeniedHandler   处理拒绝访问失败。*/
                .accessDeniedHandler(myAccessDeniedHandler)

                /** 提供注销支持。 使用WebSecurityConfigurerAdapter时，将自动应用此WebSecurityConfigurerAdapter 。
                 *  默认设置是访问URL “ / logout”将使HTTP会话无效，清理配置的所有rememberMe()身份验证，清除SecurityContextHolder ，
                 *  然后重定向到“ / login？success”，从而注销用户*/
                .and().logout().logoutSuccessHandler(myLogoutSuccessHandler)

                /** 处理身份验证表单提交。 授予权限 */
                .and().addFilter(new JwtAuthenticationFilter(authenticationManager()))
                /** 处理HTTP请求的BASIC授权标头，然后将结果放入SecurityContextHolder 。 */
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                /**不需要session */
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 
     * 因为使用了BCryptPasswordEncoder来进行密码的加密，所以身份验证的时候也的用他来判断哈、，
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    /** * 密码加密*/
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

```

### 2.5、Security身份验证

```java
import com.crush.security.entity.MyUser;
import com.crush.security.utils.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 处理身份验证表单提交。
 *
 * @author crush
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 执行实际的身份验证。
     * 该实现应执行以下操作之一：
     * 返回已验证用户的已填充验证令牌，指示验证成功
     * 返回null，表示身份验证过程仍在进行中。 在返回之前，实现应执行完成该过程所需的任何其他工作。
     * 如果身份验证过程失败，则抛出AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        //输入流中获取到登录的信息
        try {
            MyUser loginUser = new ObjectMapper().readValue(request.getInputStream(), MyUser.class);
            logger.info("loginUser===>" + loginUser);
            /**
             * authenticate
             * 尝试对传递的Authentication对象进行身份Authentication ，
             * 如果成功，则返回完全填充的Authentication对象（包括授予的权限）
             * */
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 成功验证后调用的方法
     * 如果验证成功，就生成token并返回
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
        // 所以就是JwtUser啦
        MyUser user = (MyUser) authResult.getPrincipal();
        String role = "";
        // 因为在JwtUser中存了权限信息，可以直接获取，由于只有一个角色就这么干了
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            role = authority.getAuthority();
        }
        // 根据用户名，角色创建token并返回json信息
        String token = JwtTokenUtils.createToken(user.getUsername(), role, false);
        user.setPassword(null);
        user.setToken(JwtTokenUtils.TOKEN_PREFIX + token);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(user));
    }

    /**
     * 验证失败时候调用的方法
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString( "登录失败，账号或密码错误"));
    }
}

```

### 2.6、Security授权

```java
import com.crush.security.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * 处理HTTP请求的BASIC授权标头，然后将结果放入SecurityContextHolder 。
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(request, response, chain);
    }

    /** * 这里从token中获取用户信息并新建一个token*/
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token.trim());
        String role = JwtTokenUtils.getUserRole(token);
        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null,
                    Collections.singleton(new SimpleGrantedAuthority(role))
            );
        }
        return null;
    }
}
```

### 2.7、UserDetailsService

UserDetailServiceImpl 实现了`UserDetailsService`,用来加载用户特定数据的核心接口。

```java
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crush.security.entity.MyUser;
import com.crush.security.service.IMyUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
```

### 2.7、MacLoginUrlAuthenticationEntryPoint

```java
/**
 * 
 * 身份验证没有通过回调
 */
@Component
public class MacLoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(new ObjectMapper().writeValueAsString("未登录！"));
    }
}
```

### 2.8、MyAccessDeniedHandler

```java
/**
 * 权限不足回调
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(new ObjectMapper().writeValueAsString("不好意思，你的权限不足！"));
    }
}
```

### 2.9、MyLogoutSuccessHandler

```java
/**
 * 退出回调
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(new ObjectMapper().writeValueAsString( "退出成功"));
    }
}
```

### 2.10、JWT的工具类

生成token

```java
package com.crush.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.HashMap;

public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "jwtsecretdemo";
    private static final String ISS = "echisan";

    /**
     * 过期时间是3600秒，既是1个小时
     */
    private static final long EXPIRATION = 3600L;

    /**
     * 选择了记住我之后的过期时间为7天
     */
    private static final long EXPIRATION_REMEMBER = 604800L;

    // 添加角色的key
    private static final String ROLE_CLAIMS = "rol";

    /**
     * 修改一下创建token的方法
     *
     * @param username
     * @param role
     * @param isRememberMe
     * @return
     */
    public static String createToken(String username, String role, boolean isRememberMe) {
        String token = null;
        try {
            long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
            HashMap<String, Object> map = new HashMap<>();
            map.put(ROLE_CLAIMS, role);
            token = Jwts.builder()
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    // 这里要早set一点，放到后面会覆盖别的字段
                    .setClaims(map)
                    .setIssuer(ISS)
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                    .compact();
        } catch (ExpiredJwtException e) {
            e.getClaims();
        }
        return token;
    }


    /**
     * 从token中获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 从token中获取roles
     *
     * @param token
     * @return
     */
    public static String getUserRole(String token) {
        return (String) getTokenBody(token).get(ROLE_CLAIMS);
    }

    /**
     * 是否已过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String user = encoder.encode("test");
        System.out.println(user);
    }
}

```

弄完上面这些，相关配置就都搞定了，剩下就是最简单的编码啦。

## 三、代码

### entity

```java
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("account")
public class MyUser implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    private int id;

    private String username;

    private String password;

    // 1:启用 ， 0：禁用
    @TableField(exist = false)
    private Integer enabled = 1;

    // 1：锁住 ， 0：未锁
    @TableField(exist = false)
    private Integer locked = 0;

    private String role;

    @TableField(exist = false)
    private String token;

    //授权
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        authorities.add(authority);
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return locked == 0; }

    @Override
    public boolean isCredentialsNonExpired() {  return true;  }

    @Override
    public boolean isEnabled() { return enabled == 1; }
}

```

### mapper

```java
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crush.security.entity.MyUser;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserMapper extends BaseMapper<MyUser> {}
```

### service、impl

```java
import com.baomidou.mybatisplus.extension.service.IService;
import com.crush.security.entity.MyUser;

public interface IMyUserService extends IService<MyUser> {
    
}
```

```java
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crush.security.entity.MyUser;
import com.crush.security.mapper.MyUserMapper;
import com.crush.security.service.IMyUserService;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceImpl extends ServiceImpl<MyUserMapper, MyUser> implements IMyUserService {
}

```

### controller

```java
package com.crush.security.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/all")
    String all() {
        return "在WebSecurityConfig中配置了放行，任何人都可以进行访问";
    }

    @PreAuthorize("permitAll()")
    @RequestMapping("/test")
    String test() {
        return "所有登录的人都可以访问";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/user/userList")
    String userList() {
        return "role: user";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/admin/updateUser")
    String updateUser() {
        return "role: admin";
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @RequestMapping("/admin/superAdmin")
    String superAdmin() {
        return "role: superAdmin";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/userAndAdmin")
    String userAndAdminTest() {
        return "role: admin and user";
    }

    @PreAuthorize("hasAnyRole('ADMIN')or hasAnyRole('SUPER_ADMIN')")
    @RequestMapping("/AdminAndSuperAdminTest")
    String AdminAndSuperAdminTest() {
        return "role: admin and super_admin";
    }

    // hasAnyAuthority() 也是可以多个字符串 权限验证，可以不跟ROLE_前缀
    @PreAuthorize("hasAuthority('TEST') ")
    @RequestMapping("/ceshi2")
    String ceshi2() {
        return "hasAuthority：权限验证，不过查的也是role那个字段，不过不用拼接上ROLE而已";
    }
}
```

## 四、测试

`注`:我使用的测试工具是`Postman`,另外login接口接收的数据是需要JSON类型的。

### 1）登录

注意这里的token，我们是需要把他记住，下次去请求要携带上。

![在这里插入图片描述](https://img-blog.csdnimg.cn/ae4e560d0c0f4740a7377db55ab69343.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


### 2）测试管理员

![在这里插入图片描述](https://img-blog.csdnimg.cn/5c28d7373b9d4c16829065c57d96931f.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


3）测试hasAnyAuthority （）注解

hasAnyAuthority() 也是可以多个字符串 权限验证，可以不跟ROLE_前缀

![在这里插入图片描述](https://img-blog.csdnimg.cn/ec40660771744761b6795123bfbfea9a.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


![在这里插入图片描述](https://img-blog.csdnimg.cn/fc565cc3026d4da5baca9a2ab81996d4.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


## 五、总结

Security框架和SpringBoot集成，其实上手特别快，但是如果要想研究的比较深刻的话，我觉得是比较困难的，上文讲过，security是属于一个重量级的框架，里面很多东西特别多。使用方面肯定是没有任何问题的。

你卷我卷，大家卷，什么时候这条路才是个头啊。😇（还是直接上天吧)

有时候也想停下来歇一歇，一直做一个事情，感觉挺难坚持的。😁

你好，如果你正巧看到这篇文章，并且觉得对你有益的话，就给个赞吧，让我感受一下分享的喜悦吧，蟹蟹。🤗

如若有写的有误的地方，也请大家不啬赐教！！

同样如若有存在疑惑的地方，请留言或私信，定会在第一时间回复你。

**持续更新中**
