> 今天是突然喵到这个知识点（Actuator）的，我以前确实不知道这个玩意可以这么玩，像是发现新大陆一样，整了会它，还学会了如何优雅的关闭SpringBoot应用😂。
>很喜欢一句话：`”八小时内谋生活，八小时外谋发展“`
>
>`我们："待别日相见时，都已有所成”`
>
![在这里插入图片描述](https://img-blog.csdnimg.cn/7143ec7134ff473ab54ee28a11dc07a8.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)
`看看小风景再继续哈`
@[TOC](Spring Boot 使用Actuator监控应用)
## 一、前言

相信很多小伙伴都见过Actuator，但是我想如果不是学SpringCloud的话，单纯用SpringBoot的话，应该没多少人来整它吧~~（盲猜 狗头保命）~~，不过确实在微服务中用的特别多，但是我们可以先了解吗😁

`注`:**我的SpringBoot的版本是2.5.2**。

### 1）概述：

当我们的开发工作进入尾声，部署上线之后，对于一个程序而言，可能才刚刚开始，对程序的运行情况的监控要伴随着整个生命周期。

**Actuator** 是 Spring Boot **提供的对应用系统的自省和监控的集成功能**，可以查看应用配置的详细信息，例如自动化配置信息、创建的 Spring beans 以及一些环境属性等。

### 2）分类：

Actuator 监控分成两类：原生端点和用户自定义端点；自定义端点主要是指扩展性，用户可以根据自己的实际应用，定义一些比较关心的指标，在运行期进行监控。

原生端点是在应用程序里提供众多 Web 接口，通过它们了解应用程序运行时的内部状况。原生端点又可以分成三类：

1、**应用配置类**：

- 可以查看应用在运行期的静态信息：例如自动配置信息、加载的 springbean 信息、yml 文件配置信息、环境信息、请求映射信息；

2、**度量指标类**：

- 主要是运行期的动态信息，例如堆栈、请求连接、一些健康指标、metrics 信息等；比如内存信息、线程池信息、HTTP请求统计等。

3、**操作控制类**：

- 主要是指 shutdown,用户可以发送一个请求将应用的监控功能关闭。(戏称：优雅的关闭一个SpringBoot应用)

### 3）主要接口:

| HTTP 方法 | 路径            | 描述                                                         |
| --------- | --------------- | ------------------------------------------------------------ |
| GET       | /auditevents    | 显示应用暴露的审计事件 (比如认证进入、订单失败)              |
| GET       | /beans          | 描述应用程序上下文里全部的 Bean，以及它们的关系              |
| GET       | /conditions     | 就是 1.0 的 /autoconfig ，提供一份自动配置生效的条件情况，记录哪些自动配置条件通过了，哪些没通过 |
| GET       | /configprops    | 描述配置属性(包含默认值)如何注入Bean                         |
| GET       | /env            | 获取全部环境属性                                             |
| GET       | /env/{name}     | 根据名称获取特定的环境属性值                                 |
| GET       | /flyway         | 提供一份 Flyway 数据库迁移信息                               |
| GET       | /liquidbase     | 显示Liquibase 数据库迁移的纤细信息                           |
| GET       | /health         | 报告应用程序的健康指标，这些值由 HealthIndicator 的实现类提供 |
| GET       | /heapdump       | dump 一份应用的 JVM 堆信息                                   |
| GET       | /httptrace      | 显示HTTP足迹，最近100个HTTP request/repsponse                |
| GET       | /info           | 获取应用程序的定制信息，这些信息由info打头的属性提供         |
| GET       | /logfile        | 返回log file中的内容(如果 logging.file 或者 logging.path 被设置) |
| GET       | /loggers        | 显示和修改配置的loggers                                      |
| GET       | /metrics        | 报告各种应用程序度量信息，比如内存用量和HTTP请求计数         |
| GET       | /metrics/{name} | 报告指定名称的应用程序度量值                                 |
| GET       | /scheduledtasks | 展示应用中的定时任务信息                                     |
| GET       | /sessions       | 如果我们使用了 Spring Session 展示应用中的 HTTP sessions 信息 |
| POST      | /shutdown       | 关闭应用程序，要求endpoints.shutdown.enabled设置为true       |
| GET       | /mappings       | 描述全部的 URI路径，以及它们和控制器(包含Actuator端点)的映射关系 |
| GET       | /threaddump     | 获取线程活动的快照                                           |


------

## 二、快速开始

### 步骤：

1. 新建一个SpringBoot项目
2. 导入依赖
3. 写配置
4. 启动测试（完事）（🐕保命）😁

开始啦开始啦👇

### 导入依赖：

就简单导入两个就完事啦哦。

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.2</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
</dependencies>
```

### 书写配置：

```properties
info.app.name=spring-boot-actuator
info.app.version= 1.0.0
info.app.test=test


management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
#management.endpoints.web.base-path=/monitor #代表启用单独的url地址来监控 Spring Boot 应用，为了安全一般都启用独立的端口来访问后端的监控信息

management.endpoint.shutdown.enabled=true 
#management.endpoint.shutdown.enabled=true  启用接口关闭 Spring Boot

```

`management.endpoints.web.exposure.include=*`:可以打开所有的监控点

`management.endpoints.web.exposure.exclude=beans,trace` :也可以选择打开部分监控点

Actuator 默认所有的监控点路径都在`/actuator/*`，当然如果有需要这个路径也支持定制。

```properties
management.endpoints.web.base-path=/manage
```

设置完重启后，再次访问地址就会变成`/manage/*`

### 启动测试、命令解释：

![在这里插入图片描述](https://img-blog.csdnimg.cn/ece8af6d19534cae85122ec09033982d.png#pic_center)


启动时就能看到这个/actuator就表示成功啦哦。

#### 1、actuator

/actuator：接口展示，显示actuator提供的访问接口，我们配置了是打开所有的监控点，所以是看到全部的。

![在这里插入图片描述](https://img-blog.csdnimg.cn/fa11a475715b4f2b8aee3deb450127b4.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


#### 2、health

**health** 主要用来检查应用的运行状态，这是我们使用最高频的一个监控点。

status值为UP说明应用是健康的，如果应用不健康，将会显示DOWN。

![在这里插入图片描述](https://img-blog.csdnimg.cn/11682559dfc54b55a11d61dc64f0f12b.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


#### 3、info

info 就是我们自己配置在配置文件中以 info 开头的配置信息，

```properties
info.app.name=spring-boot-actuator
info.app.version= 1.0.0
info.app.test=test
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/1ef0ce418a1e421f9bed4376e2ba0b0f.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


#### 4、beans

查看 bean 的别名、类型、是否单例、类的地址、依赖等信息。

![在这里插入图片描述](https://img-blog.csdnimg.cn/bb61b761ca414f3da4e2a09f99436ffb.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


#### 5、conditions

Spring Boot 的自动配置功能非常便利，但有时候也意味着出问题比较难找出具体的原因。使用 conditions 可以在应用运行时查看代码了某个配置在什么条件下生效，或者某个自动配置为什么没有生效。

![在这里插入图片描述](https://img-blog.csdnimg.cn/2215c1da0df740f49fb672807997ec5f.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


#### 6、heapdump

访问`http://localhost:8080/actuator/heapdump`会自动生成一个 Jvm 的堆文件 heapdump，我们可以使用 JDK 自带的 Jvm 监控工具 VisualVM 打开此文件查看内存快照。

`heapdump`这方面就牵扯到JVM知识点啦。

![在这里插入图片描述](https://img-blog.csdnimg.cn/b9145beeb7034c9ca8481c5743518bab.png#pic_center)


#### 7、shutdown

就是我前文提到的如何优雅的关闭一个SpringBoot应用。

不过要在配置中打开这个`management.endpoint.shutdown.enabled=true`。

`需要使用POST方式访问`，如果是用cmd命令行访问就为：`curl -X POST "http://localhost:8080/actuator/shutdown" `

![在这里插入图片描述](https://img-blog.csdnimg.cn/f593c3bdc5ea4286a1c5cf60c5bbd399.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


#### 8、threaddump

/threaddump 接口会生成当前线程活动的快照。这个功能非常好，方便我们在日常定位问题的时候查看线程的情况。
主要展示了线程名、线程ID、线程的状态、是否等待锁资源等信息。

![在这里插入图片描述](https://img-blog.csdnimg.cn/29b75ab39131404db00d6c46c6fe04ea.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


#### 9、metrics

访问`localhost:8080/actuator/metrics`会展示可以追踪的度量，例如jvm内存、cpu使用、jvm线程等

![在这里插入图片描述](https://img-blog.csdnimg.cn/408d4c0b7c144cff928bbed2669916a3.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


如果我们要查看某个具体的度量：

`http://localhost:8080/actuator/metrics/{MetricName}`就是如此便可。

如查看 `http.server.requests`

```apl
http://localhost:8080/actuator/metrics/http.server.requests
```

可以查看到我们请求过的request、请求方法等。

![在这里插入图片描述](https://img-blog.csdnimg.cn/9b31b1cf4fc04ff19d4b12ec998a6803.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


#### 10、endpoint

 默认情况，上述所有的endpints都是打开的，除了shutdown endpoint。如果我们想要控制单个endpoint 开关，可以进行如下配置：

```objectivec
management.endpoint.<id>.enabled=true/false
```

日志也是一样的，就没有写出来啦。

......😁还有很多的就没有测试啦，大家可以手动测试 （~~狗头保命哦~~）😀

------

## 三、自言自语

带着好奇和求知欲来学习，只想让自己能够变得更加优秀。

希望`我们：待别日相见时，都已有所成`。

加油！！😁


