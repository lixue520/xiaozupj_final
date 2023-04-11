

> 异步任务在很多地方都用的特别多，例如注册发送邮件，发送短信等等。本文是讲解一个简单的SpringBoot异步任务的实现，技术总是慢慢进步的啦。
>
> （狗头保命）👩‍💻
>
> 很喜欢一句话：`”八小时内谋生活，八小时外谋发展“`
>
> `我们："待别日相见时，都已有所成”`😁

![在这里插入图片描述](https://img-blog.csdnimg.cn/603d563f8b804002b3468d835e07d09c.JPG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)
`校园一角`

@[TOC](SpringBoot异步任务Async及邮件服务搭配起来更棒哦！！!)

## 一、前言

### 1）概述：

**“异步”(Asynchronous)与“同步”(Synchronous)相对，异步不用阻塞当前线程来等待处理完成，而是允许后续操作，直至其它线程将处理完成，并回调通知此线程。也就是说，异步永远是非阻塞的(non-blocking)。**

异步操作的程序，在代码执行时，不等待异步调用的语句返回结果就执行后面的程序。当任务间没有先后顺序依赖逻辑的时候，可以使用异步。

同步就是串行。在这里举个注册发送邮件的例子：

**同步**：

1. 发送注册请求-->注册成功-->执行发送邮件方法--->发送成功-->返回信息前端。

**异步**：

- 主线程：发送注册请求-->注册成功-->调用发送邮件方法--->返回信息给前台

- 线程二：--------------------------------------执行发送邮件方法--------------------------

### 2）使用场景：

使用到异步任务的地方非常多。

就例如：

1. 注册发送邮件，发送短信
2. App消息推送
3. 节省运维凌晨发布任务时间提供效率



## 二、代码实现

案例：注册发送邮件

### 1、步骤：

1. 新建SpringBoot项目
2. 导入依赖
3. 书写配置
4. 编码
5. 启动测试

### 2、导入依赖：

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

### 3、yml配置文件

```yml
server:
  port: 8087
spring:
  application:
    name: springboot-scheduled
  mail:
    # 配置 SMTP 服务器地址
    host: smtp.qq.com
    # 发送者邮箱
    username: 790933839@qq.com
    # 配置密码，注意不是真正的密码，而是刚刚申请到的授权码
    password: vjstfghblprwbdbd
    # 端口号465或587
    port: 587
    # 默认的邮件编码为UTF-8
    default-encoding: UTF-8
    # 配置SSL 加密工厂
    properties:
      mail:
        smtp:
          socketFactoryClass: javax.net.ssl.SSLSocketFactory
        #表示开启 DEBUG 模式，这样，邮件发送过程的日志会在控制台打印出来，方便排查错误
        debug: true
```

### 4、config层

ThreadPoolTaskExecutorConfig 线程池配置

```java
@Configuration
@EnableAsync // 开启异步配置
public class ThreadPoolTaskExecutorConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置核心线程数
        executor.setCorePoolSize(10);
        //设置最大线程数
        executor.setMaxPoolSize(20);
        //缓冲队列200：用来缓冲执行任务的队列
        executor.setQueueCapacity(200);
        //线程活路时间 60 秒
        executor.setKeepAliveSeconds(60);
        //线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setThreadNamePrefix("taskExecutor-");
        //设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
}
```

### 5、实体类

```java
@Data
@Accessors(chain = true)
public class Account {
    private String username;
    private String password;
    private String email;
}
```

### 6、service层

```java
public interface EmailService {
    /**用于注册成功后发送邮件 @param account 账号信息*/
    void senderEmail(Account account);
}


@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    
	@Autowired
    private JavaMailSender javaMailSender;

    @Async("taskExecutor")
    @Override
    public void senderEmail(Account account) {
        log.info(Thread.currentThread().getName());
        //一个复杂的邮件
        MimeMessage message = this.javaMailSender.createMimeMessage();
        try {
            //组装
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //正文
            //主题
            helper.setSubject("小可爱，欢迎你的到来哦！！！");
            //开启html模式
            helper.setText("<h1>小可爱,我想你一定会喜欢这里吧！！！</h1>" +
                    "<p>你的账号为:"+account.getUsername()+"</p>" +
                    "<p>你的密码为："+account.getPassword()+"</p>", true);
            //附件
            helper.addAttachment("1.jpg", new File("C:\\Users\\ASUS\\Desktop\\杂七杂八\\杂图\\2.gif"));
            helper.setTo(account.getEmail());
            helper.setFrom("790933839@qq.com");
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
```

### 7、controller

```java
@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {

    private static List<Account> accountList=new ArrayList<Account>();

    @Autowired
    private  TaskExecutor taskExecutor;

    @Autowired
    private  EmailService emailService;

    /**写一个注册发送邮件的栗子 */
    @PostMapping("/register")
    public String register(@RequestBody  Account account){
        accountList.add(account);
        emailService.senderEmail(account);
        log.info(Thread.currentThread().getName());
        return "OK";
    }
}
```

### 8、测试

![在这里插入图片描述](https://img-blog.csdnimg.cn/8298361bd3bb4966aacce0297573c446.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


![在这里插入图片描述](https://img-blog.csdnimg.cn/c2ec32e3fbb949b9b6990590f5c0d43e.png#pic_center)


我们可以看到在执行发送邮件方法时，并非是主线程在执行，而是从线程池中拉了一个线程来执行，做到了异步操作。



## 三、自言自语

一个小小的Demo，不足之处，请见谅！

你好，我是博主`宁在春`😁

如果你看到这篇文章，并且觉得对你有益的话，就给个赞吧，让我感受一下分享的喜悦吧，蟹蟹。🤗

如若有写的有误的地方，也请大家不啬赐教！！

同样如若有存在疑惑的地方，请留言或私信，定会在第一时间回复你。

**持续更新中**
