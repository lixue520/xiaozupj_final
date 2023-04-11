> 现在大多数项目都会输出日志或保存日志，现在这个大数据时代，数据已经是一种非常非常重要的资源了。
>
> 日志也有很大作用的，不要小瞧它哦。😁
>很喜欢一句话：`“八小时内谋生活，八小时外谋发展”`。
>`如果你也喜欢，让我们一起坚持吧！！`
>`共勉`😁
>
![在这里插入图片描述](https://img-blog.csdnimg.cn/2c0c662d0470496fb02aedfd895dcf21.JPG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


`我们：待别日相见时，都已有所成`
@[TOC](SpringBoot日志收集-Aop方式-存进数据库)
## 一、前言

`本文使用的SpringBoot版本为：2.5.2`



### 1）概述：

`日志`：**网络设备、系统及服务程序等，在运作时都会产生一个叫log的事件记录；每一行日志都记载着日期、时间、使用者及动作等相关操作的描述**。

### 2）介绍：

**Windows网络操作系统设计有各种各样的日志文件**，如应用程序日志，安全日志、系统日志、Scheduler服务日志、FTP日志、WWW日志、DNS服务器日志等等，这些根据你的系统开启的服务的不同而有所不同。

`本文介绍的更多的是偏向于行为日志，并非系统日志级别的`。

我们在系统上进行一些操作时，这些日志文件通常会记录下我们操作的一些相关内容，这些内容也许对我们来说并没有什么用处，但是对系统安全工作人员却相当有用。

比如说有人对系统进行了IPC探测，系统就会在安全日志里迅速地记下探测者探测时所用的IP、时间、用户名等，用FTP探测后，就会在FTP日志中记下IP、时间、探测所用的用户名等。

### 3）使用场景：

简单介绍几个~~（我还菜很多不晓得，狗头保命😂）~~

1. 排查bug，从日志查看错误出现地方
2. 异地登录。（登录日志会记录下你的Ip）

**对了哈，本文更多的是提供一个方法、思路和用一个完整案例来让大家对SpringBoot-注解Aop记录日志有一个认识**

## 二、前期准备

**案例**：

使用SpringBoot的Aop方式，将访问者的信息写入数据库中。

**项目结构**：

![在这里插入图片描述](https://img-blog.csdnimg.cn/1e9b2c7d58fc4b709bb74729cf3b91fe.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


`说明`：因为习惯了用MybatisPlus，拿了之前的完整配置，所以看起来java文件有多，但是关于log的其实并不复杂，代码中也带有注释， 请放心食用。

对MybatisPlus感兴趣的可以点👉[SpringBoot整合MybatisPlus](https://blog.csdn.net/weixin_45821811/article/details/119234648?spm=1001.2014.3001.5501)

### 2.1、数据库

tb_user表

```sql
CREATE TABLE `tb_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `passwrod` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deleted` int(1) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `tb_user` VALUES ('1', '宁在春', '123456', 0, '2021-07-23 14:32:46', '2021-07-29 23:56:10');
INSERT INTO `tb_user` VALUES ('2', '青冬栗', 'qwerasd', 0, '2021-07-23 15:02:02', '2021-07-23 15:49:55');
```

tb_log表

```sql
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(10) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `login_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int(10) NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `tb_log` VALUES ('e5b49465-b20a-453f-b15c-b284733f2f8e', 1, '宁在春', '0:0:0:0:0:0:0:1', 1, '127.0.0.1', '查询用户信息', '2021-08-15 01:04:31', '', '2021-08-15 01:04:31');
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
    <!--spring切面aop依赖-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.4.1</version>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.2.6</version>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.72</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.23</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-all</artifactId>
        <version>5.6.5</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

依赖都是常用的哈，没啥要说的哈。😀

### 2.3、yml配置文件

```yml
server:
  port: 8091
spring:
  application:
    name: springboot-log
  # 数据源配置
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    # 阿里的数据库连接池
    druid:
      username: root
      password: 123456
      url: jdbc:mysql://localhost:3306/commons_utils?serverTimezone=UTC&useSSL=false&characterEncoding=utf8&serverTimezone=GMT
      # 初使化连接数(向数据库要五个连接)
      initial-size: 5
      # 最小连接数(常住10个连接)
      min-idle: 10
      # 最大连接数(最多获得10个连接，多到10个数据库将进入一个阻塞状态，等待其他连接释放)
      max-active: 20
      # 获取连接最长等待时间，单位毫秒
      max-wait: 10000
      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
      timeBetweenEvictionRunsMillis: 60000
      # 配置一个连接在池中最小生存的时间，单位是毫秒
      minEvictableIdleTimeMillis: 300000
      # 配置一个连接在池中最大生存的时间，单位是毫秒
      maxEvictableIdleTimeMillis: 900000
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
mybatis-plus:
  configuration:
    cache-enabled: true #开启缓存
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl #开启sql日志
  mapper-locations: classpath:/mapper/*Mapper.xml
  global-config:
    db-config:
      logic-delete-field: flag  # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
```

### 2.3、配置自定义log注解类

如果需要收集多种日志的话，可以做扩展，增加注解也可，用编码也可，当然如果项目多的话，那么必然是要抽取出来才是最合适的。

~~（经验不足、如有不妥，请及时提出，蟹蟹各位大佬😁）~~

```java
/**
 * 配置自定义log注解类
 * @author crush
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface MyLog {
    /** 操作事件     */
    String operation () default "";

    /** 日志类型 */
    int type ();
}
```

### 2.4、SysLogAspect：切面处理类

```java
import cn.hutool.core.lang.UUID;
import com.crush.log.annotation.MyLog;
import com.crush.log.entity.LogOperation;
import com.crush.log.entity.LogUser;
import com.crush.log.mapper.LogOperationMapper;
import com.crush.log.utils.IpUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/** 系统日志：切面处理类 */
@Aspect
@Component
public class SysLogAspect {
	/**我这里是使用log4j2把一些信息打印在控制台上面，可以不写 */
    private static final Logger log = LogManager.getLogger(SysLogAspect.class);

	/**操作数据库 */
    @Autowired
    private LogOperationMapper logOperationMapper;

    /**
     *  定义切点 @Pointcut
     *  在注解的位置切入代码
     *  这里的意思就是注解写在那个方法上，那个方法就是被切入的。
     */
    @Pointcut("@annotation(com.crush.log.annotation.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @Before("logPoinCut()")         //AfterReturning
    public void saveOperation(JoinPoint joinPoint) {
        log.info("---------------接口日志记录---------------");
        //用于保存日志
        LogOperation logOperation = new LogOperation();

        // 这里是获得当前请求的request
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        String requestURL = request.getRequestURL().toString();
        logOperation.setUrl(requestURL);

        // 客户端ip  这里还可以与之前做一个比较，如果不同的话，就给他推送消息什么的，说异地登录 什么的。 
        String ip = IpUtils.getIpAddr(request);
        logOperation.setLoginIp(ip);

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作--方法上的Log的值
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            //保存操作事件
            String operation = myLog.operation();
            logOperation.setOperation(operation);

            //保存日志类型  这里也可以做扩展 根据不同的类型，你可以做不同的操作 
            int type = myLog.type();
            logOperation.setType(type);

            log.info("operation="+operation+",type="+type);
        }

        // 操作人账号、姓名（需要提前将用户信息存到session）
        // 因为这里是模拟 所以偷懒用了个 session 
        // 实际上用了security 获取的应该是当前授权对象的信息 而不是从session 中获取 
        // 也或者说是从 redis 中获取，这只是提供一个思路，请见谅
        LogUser user = (LogUser) request.getSession().getAttribute("user");
        if(user != null) {
            String userId = user.getId();
            String userName = user.getUsername();
            logOperation.setUserId(userId);
            logOperation.setUsername(userName);
            System.out.println(user);
        }
        log.info("url="+requestURL,"ip="+ip);
       
        //调用service保存Operation实体类到数据库
        //我id使用的是UUID，不需要的可以注释掉
        String id = UUID.randomUUID().toString().replace("-","");
        logOperation.setId(id);
        logOperationMapper.insert(logOperation);
    }
}
```

### 2.5、MybatisPlus相关配置类

MybatisPlusConfig

```java
/**
 * @EnableTransactionManagement :开启事务
 * @Author: crush
 * @Date: 2021-07-23 14:14
 * version 1.0
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.crush.log.mapper")
public class MybatisPlusConfig {

    /*** 分页*/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 注册乐观锁 插件
        return mybatisPlusInterceptor;
    }

    /** 配置数据源 druid*/
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid")
    public DruidDataSource druidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}

```

MyMetaObjectHandler：自动填充

```java
/**
 * 填充创建和修改时间
 * @Author: crush
 * @Date: 2021-07-23 14:14
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }
}
```



LocalDateTimeSerializerConfig：配置全局的LocalDateTime格式化

```java
@Configuration
public class LocalDateTimeSerializerConfig {
    @Value("${spring.jackson.date-format}")
    private String DATE_TIME_PATTERN;
    
    @Value("${spring.jackson.date-format}")
    private  String DATE_PATTERN ;
    /*** string转localdate*/
    @Bean
    public Converter<String, LocalDate> localDateConverter() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {
                if (source.trim().length() == 0) {
                    return null;
                }
                try {
                    return LocalDate.parse(source);
                } catch (Exception e) {
                    return LocalDate.parse(source, DateTimeFormatter.ofPattern(DATE_PATTERN));
                }
            }
        };
    }

    /** * string转localdatetime*/
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                if (source.trim().length() == 0) {
                    return null;
                }
                // 先尝试ISO格式: 2019-07-15T16:00:00
                try {
                    return LocalDateTime.parse(source);
                } catch (Exception e) {
                    return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
                }
            }
        };
    }

    /** * 统一配置 LocalDateTime 格式化*/
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        JavaTimeModule module = new JavaTimeModule();
        LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
        return builder -> {
            builder.simpleDateFormat(DATE_TIME_PATTERN);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_PATTERN)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
            builder.modules(module);
        };
    }
}
```

### 2.6、IpUtils

```java
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取IP方法
 */
public class IpUtils
{
    public static String getIpAddr(HttpServletRequest request)
    {
        if (request == null)
        {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static boolean internalIp(String ip)
    {
        byte[] addr = textToNumericFormatV4(ip);
        return internalIp(addr) || "127.0.0.1".equals(ip);
    }

    private static boolean internalIp(byte[] addr)
    {
        if (addr == null || addr.length < 2)
        {
            return true;
        }
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        // 10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        // 172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        // 192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0)
        {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4)
                {
                    return true;
                }
            case SECTION_5:
                if (b1 == SECTION_6) {
                    return true;
                }
            default:
                return false;
        }
    }

    /**
     * 将IPv4地址转换成字节
     * 
     * @param text IPv4地址
     * @return byte 字节
     */
    public static byte[] textToNumericFormatV4(String text)
    {
        if (text.length() == 0)
        {
            return null;
        }

        byte[] bytes = new byte[4];
        String[] elements = text.split("\\.", -1);
        try
        {
            long l;
            int i;
            switch (elements.length)
            {
                case 1:
                    l = Long.parseLong(elements[0]);
                    if ((l < 0L) || (l > 4294967295L))
                        return null;
                    bytes[0] = (byte) (int) (l >> 24 & 0xFF);
                    bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 2:
                    l = Integer.parseInt(elements[0]);
                    if ((l < 0L) || (l > 255L))
                        return null;
                    bytes[0] = (byte) (int) (l & 0xFF);
                    l = Integer.parseInt(elements[1]);
                    if ((l < 0L) || (l > 16777215L))
                        return null;
                    bytes[1] = (byte) (int) (l >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 3:
                    for (i = 0; i < 2; ++i)
                    {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L))
                            return null;
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    l = Integer.parseInt(elements[2]);
                    if ((l < 0L) || (l > 65535L))
                        return null;
                    bytes[2] = (byte) (int) (l >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 4:
                    for (i = 0; i < 4; ++i)
                    {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L))
                            return null;
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    break;
                default:
                    return null;
            }
        }
        catch (NumberFormatException e)
        {
            return null;
        }
        return bytes;
    }

    public static String getHostIp()
    {
        try
        {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException ignored)
        {

        }
        return "127.0.0.1";
    }

    public static String getHostName()
    {
        try{
            return InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException ignored) {
        }
        return "未知";
    }
}
```

## 三、业务代码

我这里没有写查看日志的接口，存数据库，管理员可以随时查看这些信息，也可以使用web页面、会方便许多。

### 1、entity

LogUser

```java
/**
 * @Author: crush
 * @Date: 2021-08-14 8:43
 * version 1.0
 */
@Data
@Accessors(chain = true)
@TableName("tb_user")
public class LogUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String passwrod;

    /*** 逻辑删除字段 */
    @TableLogic
    private Integer deleted;

    /*** 创建时间*/
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /*** 修改时间*/
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
```



```java
package com.crush.log.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志表
 * @author crush
 */
@Data
@Accessors(chain = true)
@TableName("tb_log")
public class LogOperation implements Serializable {

    private static final long serialVersionUID = 7925874058046995566L;

    private String id;
    /*** 用户id 操作人ID */
    private String userId;
    /** * 用户名称 关联admin_user */
    private String username;
    /** * 登录ip */
    private String loginIp;
    /** * 操作类型(0登录、1查询、2修改) 这个根据自己需求定义即可 ，还有很多其他方式，这个并不完善，只是刚刚够用的那种  */
    private int type;

    /** *  操作的url*/
    private String url;
    /**  * 操作内容 */
    private String operation;
    /** * 操作时间*/
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /*** 备注*/
    private String remark;
    /*** 修改时间*/
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
```

### 2、mapper

```java
@Repository
@Mapper
public interface LogOperationMapper extends BaseMapper<LogOperation> {
}
```

```java
@Repository
public interface LogUserMapper extends BaseMapper<LogUser> {
}
```

### 3、Service

```java
public interface ILogUserService extends IService<LogUser> {
}
```

```java
@Service
public class LogUserServiceImpl extends ServiceImpl<LogUserMapper, LogUser> implements ILogUserService {
}
```

### 4、Controller

```java
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crush.log.annotation.MyLog;
import com.crush.log.entity.LogUser;
import com.crush.log.service.ILogUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger log = LogManager.getLogger(UserController.class);

    @Autowired
    private ILogUserService userService;


    /**
     * 假装登录，将用户信息存到session（方法是我之前写的懒得改，）
     * */
    @RequestMapping("/login")
    public String login(@RequestBody LogUser logUser,HttpServletRequest request){
        QueryWrapper<LogUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",logUser.getUsername()).eq("passwrod",logUser.getPasswrod());
        LogUser user = userService.getOne(wrapper);
        if(user!=null){
            request.getSession().setAttribute("user",user);
            return "登录成功";
        }
        return "登录失败";
    }

    /**记录日志*/
    @MyLog(operation = "查询用户信息",type = 1)
    @RequestMapping("/log")
    public List<LogUser> insertLog(HttpServletRequest request){
        List<LogUser> users = userService.list();
        return users;
    }
}
```

记得写个主启动类，这我就不写啦。

### 5、测试

直接启动测试，先登录，再访问`/log`.

![在这里插入图片描述](https://img-blog.csdnimg.cn/cc165bf386794f988a1cc109a8e67d8b.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


再访问`/log`

![在这里插入图片描述](https://img-blog.csdnimg.cn/ecd914704d9b4493a15b363400e28633.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


我们再看一下后台输出：

![在这里插入图片描述](https://img-blog.csdnimg.cn/ba4499cd60214ec9a5680e8283d8dc03.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


## 四、自言自语

本文只是给大家提供一个小思路，代码写的较为粗糙，请见谅。😁

还有很多地方可以扩展和完善，大家感兴趣的话，可以多试一试，这样学习才有乐趣啦。😂

日志的话他还会分很多类的，大家可以根据自己的需求扩展。

![在这里插入图片描述](https://img-blog.csdnimg.cn/a1e2054a3c09411faad8216439e4cdef.gif#pic_center)


我知道咱们CSDN的大佬，讲话又好听，长的又帅，女朋友随便new，给小弟一个赞👍，这肯定的吧。😁




