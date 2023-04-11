> 在项目常常会出现一些意料之外的错误，不能及时处理，大家都懂的哈。😁当然现在有很多监控服务，我这点能力是不够写的哈。☺
>
> 就有了这么一个小小的思路，用邮件服务来提醒出现异常啦。👩‍💻
>
> ~~（狗头保命）👩‍💻~~
>
> 很喜欢一句话：`”八小时内谋生活，八小时外谋发展“`
> `我们："待别日相见时，都已有所成”`😁

![在这里插入图片描述](https://img-blog.csdnimg.cn/15e753bd8d1e465a8b2c681d16b469bf.JPG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)

`曾经想和女朋友一起去看的生活这么久的城市中的一个小小地方，事实上去是去了，只是一个人去了`。
 `21.8.14`

 ---
 @[TOC](SpringBoot基于异常处理exception发送邮件消息提醒)
 

## 一、前言

**SpringBoot异步实现发送邮件服务**

### 1）异常处理概述：

**异常处理**，是编程语言或计算机硬件里的一种机制，用于处理软件或信息系统中出现的异常状况（即超出程序正常执行流程的某些特殊条件）。**通过异常处理，我们可以对用户在程序中的非法输入进行控制和提示，以防程序崩溃**。以返回正确的信息给前台。

### 2）异常处理：

SpringBoot中的异常处理分为局部处理异常和全局处理异常。方式稍稍有些差异。

2.1、**局部异常处理**：

- 是在当前类中进行处理，复用性太低，不推荐使用，所以只是简单举个例子哈。
- `@ExceptionHandler` 注解处理局部异常

例如：

```java
@Controller
public class ExceptionController {
	
	private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);
	
	@RequestMapping("/exceptionMethod")
	public String exceptionMethod(Model model) throws Exception {
		model.addAttribute("msg", "没有抛出异常");
		int num = 1/0;
		log.info(String.valueOf(num));
		return "home";
	}
	
	/**
	 * 描述：捕获 ExceptionController 中的 ArithmeticException 异常
	 * @param model 将Model对象注入到方法中
	 * @param e 将产生异常对象注入到方法中
	 * @return 指定错误页面
	 */
	@ExceptionHandler(value = {ArithmeticException.class})
	public String arithmeticExceptionHandle(Model model, Exception e) {
		model.addAttribute("msg", "@ExceptionHandler" + e.getMessage());
		log.info(e.getMessage());
		return "error";
	}
}
```

2.2、**全局异常处理**：

- 使用 `@ControllerAdvice` + `@ExceptionHandler` 注解能够处理全局异常，这种方式推荐使用，可以根据不同的异常对不同的异常进行处理。

这种稍后会在案例中讲解。

全局处理还有一种方式：配置 `SimpleMappingExceptionResolver` 类处理异常

因为现在使用SpringBoot更多的是使用前后端分离的方式，这种和视图的关联就不怎么合适，所以也归入不推荐的方式中啦。

```java
@Configuration
public class GlobalException {
	@Bean
	public SimpleMappingExceptionResolver
		getSimpleMappingExceptionResolver(){
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		/*
		 * 参数一：异常的类型，注意必须是异常类型的全名
		 * 参数二：视图名称
		 */
		mappings.put("java.lang.ArithmeticException", "errors");
		
		//设置异常与视图映射信息的
		resolver.setExceptionMappings(mappings);
		return resolver;
	}
}
```

## 二、环境准备

**案例**：

我这里只是简单模拟了一个最简单的异常来测试哈。就是请求方法出错`HttpRequestMethodNotSupportedException`,然后发送邮件哈。

**项目结构**：

![在这里插入图片描述](https://img-blog.csdnimg.cn/e0b7837fee02439b9d03e4f13d2fcde5.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


下面来看具体的代码：

### 2.1、导入依赖

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.2</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
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
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
</dependencies>
```

### 2.2、yml配置文件

```yml
server:
  port: 8092
spring:
  application:
    name: springboot-exception-email
  mail:
    # 配置 SMTP 服务器地址
    host: smtp.qq.com
    # 发送者邮箱
    username: 
    # 配置密码，注意不是真正的密码，而是刚刚申请到的授权码
    password:
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

### 2.3、一些公共的类

ThreadPoolTaskExecutorConfig ：线程池配置类

```java
/**
 * 异步线程池ThreadPoolExecutor 配置类
 * @author cuberxp
 * @since 1.0.0
 * Create time 2020/4/2 23:23
 */
@Configuration
@EnableAsync
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

ResponseDto：统一返回给前端的数据

```java
/**
 * @author crush
 */
@Data
@NoArgsConstructor
public class ResponseDto<T> {
    /** * 错误码*/
    private Integer code;
    /** * 提示信息*/
    private String msg;
    /** * 具体的内容*/
    private T data;

    public ResponseDto(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
    public static ResponseDto success(Object object){
        ResponseDto result = new ResponseDto();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(object);
        return result;
    }
}
```

一些基础环境就准备好了，剩下就是最简单的编码啦哈。

### 2.4、全局异常处理

```java
/**
 * @author crush
 * @ControllerAdvice
 * @ResponseBody //表示返回的对象，Spring会自动把该对象进行json转化，最后写入到Response中。
 */
@ControllerAdvice
@ResponseBody
@Component
public class GlobalExceptionHandler {

    @Autowired
    EmailService emailService;
    /**
     * //表示让Spring捕获到所有抛出的SignException异常，并交由这个被注解的方法处理。
     * //表示设置状态码
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    ResponseDto handleException(HttpRequestMethodNotSupportedException exception){
        LogEmail email = new LogEmail()
                .setToEmail("951930136@qq.com")
                .setSubject("异常报告")
                .setContext(exception.getMessage());
        emailService.senderEmail(email);
        return new ResponseDto(405,exception.getMessage());
    }
}
```

## 三、业务代码

### 3.1、entity

```java
@Data
@Accessors(chain = true)
public class LogEmail {
    private String toEmail;
    private String fromEmail;
    private String subject;
    private String context;
}
```

### 3.2、Service

```java
public interface EmailService {
    /*** 出现异常 发送短信*/
    void senderEmail(LogEmail logEmail);
}

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private  JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Async("taskExecutor")
    @Override
    public void senderEmail(LogEmail logEmail) {
        log.info(Thread.currentThread().getName());
        //一个复杂的邮件
        MimeMessage message = this.javaMailSender.createMimeMessage();
        try {
            //组装
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //正文
            //主题
            helper.setSubject(logEmail.getSubject());
            //开启html模式
            helper.setText("<h1>"+logEmail.getContext()+"</h1>" ,true);
            //附件
            helper.addAttachment("1.jpg", new File("C:\\Users\\ASUS\\Desktop\\杂七杂八\\杂图\\2.gif"));
            helper.setTo(logEmail.getToEmail());
            helper.setFrom(fromEmail);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
```

### 3.3、Controller

```java
@RestController
public class DemoController {
    @GetMapping("/test")
    public ResponseDto test(){![在这里插入图片描述](https://img-blog.csdnimg.cn/d5ba2fdfee864dec834cac791c09ef65.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)

        return ResponseDto.success("我喜欢你！！！");
    }
}
```

业务代码就这些了，不过记得要补充一个主启动类哈，这个我就不贴啦哈。

## 四、测试

测试特别简单，先看正常的哈。

我们用正确的`GET`方式发送请求是完全没有问题的，返回也是正确的数据。

![在这里插入图片描述](https://img-blog.csdnimg.cn/156aa44936254e38a56baca1da5e133b.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)



接下来我们用`POST`方式来请求，看能不能正确的调用邮件方法发送邮件啊😁

![在这里插入图片描述](https://img-blog.csdnimg.cn/fe62a946d53441b396d87f062b245aec.gif#pic_center)
证明我们确实已经抓住了这个异常，并且也成功发送了邮件。

这里只是一个小小的Demo，处理的异常也比较简单，如果真正要去用的话，肯定是不会放在这样的异常上面的，而是一些更加重要的异常上面，细节也会更加的完善。邮件可以一次性提醒很多人，方便应用程序的及时维护。

## 五、自言自语

![在这里插入图片描述](https://img-blog.csdnimg.cn/img_convert/6154a0aef772c0bd4eb7ffef2999d804.gif)


我知道咱们CSDN的大佬，讲话又好听，长的又帅，女朋友随便new，给小弟一个赞👍，这肯定的吧。😁

你好，如果你正巧看到这篇文章，并且觉得对你有益的话，就给个赞吧，让我感受一下分享的喜悦吧，蟹蟹。🤗

如若有写的有误的地方，也请大家不啬赐教！！

同样如若有存在疑惑的地方，请留言或私信，定会在第一时间回复你。

**持续更新中**



