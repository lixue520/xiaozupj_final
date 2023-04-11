> 上一篇博客写了👉[Docker搭建Redis Cluster 集群环境](https://juejin.cn/post/6992872034065727525)
>
> 我自己是认为对于每个知识点，光看了不操作是没有用的（遗忘太快...），多少得在手上用上几回才可以，才能对它加深印象。
>
> 昨天搭建了Redis Cluster 集群环境，今天就来拿它玩一玩Redis 发布/订阅模式吧
>
> 很喜欢一句话：”八小时内谋生活，八小时外谋发展“。
>
> `共勉`.😁

![在这里插入图片描述](https://img-blog.csdnimg.cn/2ea4dad4d026415b805aea033960605c.JPG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)
`地点`：😂不知道
`作者`：L
@[TOC](SpringBoot 整合Redis集群配置 实现发布/订阅模式)
## 一、前言

其实光从代码层面上讲，其实没有什么变化，主要是变化是关于Redis的配置需要更改为集群配置而已，之前接触过redis的话，那么就只需要看一下redis集群配置文件即可了。

对redis实现发布/订阅感兴趣的话，那就可以接着看下去了哈。

`发布/订阅模式` ：所谓发布/订阅模式，其实就是和你关注微信公众号一样的意思。

举个例子：你订阅了两个微信公众号（一个叫青年湖南，一个叫央视新闻），假如我也订阅了青年湖南，某一天央视发布了一条新新闻，你能收到，我没有关注，则我不能收到。但是某周看青年大学习发布王冰冰叫你去学习时，你我都订阅了，就都可以收到。

## 二、前期准备

两份配置文件都有。

单机也是可以的，想一起搭集群玩的可以👉[Docker搭建Redis Cluster 集群环境](https://juejin.cn/post/6992872034065727525)。

### 2.1、项目结构：

![在这里插入图片描述](https://img-blog.csdnimg.cn/55aec73dcec74c0b8ad7eeb78a3200ea.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


### 2.2、依赖的jar包

我这里是因为是习惯创建maven项目，然后将SpringBoot的版本抽出来，方便控制版本。

```java
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.2</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-pool2</artifactId>
        <version>2.4.3</version>
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
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.72</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.20</version>
    </dependency>
</dependencies>
```

### 2.3 、yml配置文件

单机配置文件

```yml
spring:
  redis:
    database: 0
    port: 6379
    host: localhost
    password:
    lettuce:
      pool:
        # 连接池最大连接数（使用负值表示没有限制）
        max-active: 1024
        # 连接池最大阻塞等待时间（使用负值表示没有限制）
        max-wait: 10000
        # 连接池中的最大空闲连接
        max-idle: 200
        # 连接池中的最小空闲连接
        min-idle: 0
    # 连接超时时间（毫秒）
    timeout: 10000
```

redis集群配置文件

```yml
server:
  port: 8089
spring:
  application:
    name: springboot-redis
  redis:
    password: 1234
    cluster:
      nodes:
        - IP地址:6379
        - IP地址:6380
        - IP地址:6381
        - IP地址:6382
        - IP地址:6383
        - IP地址:6384
      max-redirects: 3  # 获取失败 最大重定向次数
    lettuce:
      pool:
        max-active: 1000  #连接池最大连接数（使用负值表示没有限制）
        max-idle: 10 # 连接池中的最大空闲连接
        min-idle: 5 # 连接池中的最小空闲连接

#===========jedis配置方式=============================================
#    jedis:
#      pool:
#        max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）
#        max-wait: -1ms      # 连接池最大阻塞等待时间（使用负值表示没有限制）
#        max-idle: 10      # 连接池中的最大空闲连接
#        min-idle: 5       # 连接池中的最小空闲连接
#
```

## 三、编码

### 3.1、config层

redis配置类

```java
import com.crush.ps.subscribe.AConsumerRedisListener;
import com.crush.ps.subscribe.BConsumerRedisListener;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * redis 配置类
 * 1. 设置RedisTemplate序列化/返序列化
 * 2. 监听消息
 * @author cuberxp
 * @since 1.0.0
 * Create time 2020/1/23 0:06
 */
@Configuration
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    @Autowired
    AConsumerRedisListener aConsumerRedisListener;

    @Autowired
    BConsumerRedisListener bConsumerRedisListener;

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        //将消息侦听器添加到（可能正在运行的）容器中。 如果容器正在运行，则侦听器会尽快开始接收（匹配）消息。
        // a 订阅了 topica、topicb 两个 频道
        container.addMessageListener(aConsumerRedisListener, new PatternTopic("topica"));
        container.addMessageListener(aConsumerRedisListener, new PatternTopic("topicb"));

        // b 只订阅了 topicb  频道
        container.addMessageListener(bConsumerRedisListener, new PatternTopic("topicb"));
        return container;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //设置value hashValue值的序列化
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(
                Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(om);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        //key hasKey的序列化
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
```

### 3.2、订阅者

我在这边写了两个订阅者,方便演示例子罢了。

A消费者

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author crush
 * MessageListener ： Redis中发布的消息的侦听器。
 */
@Component
public class AConsumerRedisListener implements MessageListener {

    @Autowired
    private  RedisTemplate<String, Object> redisTemplate;

     /**
     * @param message 传递过来的信息数据
     * @param pattern 频道
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        doBusiness(message);
    }

    /**
     * 打印 message body 内容
     *
     * deserialize 从给定的二进制数据反序列化一个对象。
     * @param message
     */
    public void doBusiness(Message message) {
        Object value = redisTemplate.getValueSerializer().deserialize(message.getBody());
        System.out.println("A==>consumer message: " + value.toString());
    }

}
```

B消费者：

```java
package com.crush.ps.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author crush
 */
@Component
public class BConsumerRedisListener implements MessageListener {

    @Autowired
    private  RedisTemplate<String, Object> redisTemplate;

    /**
     * @param message 传递过来的信息数据
     * @param pattern 频道
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        doBusiness(message);
    }

    /**
     * 打印 message body 内容
     *
     * deserialize 从给定的二进制数据反序列化一个对象。
     * @param message
     */
    public void doBusiness(Message message) {
        Object value = redisTemplate.getValueSerializer().deserialize(message.getBody());
        System.out.println("B==>consumer message: " + value.toString());
    }
}
```

### 3.3、AnnouncementMessage实体类

就是自己写的传递消息的实体类，（AnnouncementMessage 意思就是拿来模拟发布公布的实体类）

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author crush
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementMessage implements Serializable {

    private static final long serialVersionUID = 8632296967087444509L;

    /**
     * 公告信息id
     */
    private String id;

    /**
     * 公告内容
     */
    private String content;
}
```

## 四、测试

```java
@SpringBootTest
public class SubscribeTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testSubscribe() {
        String achannel = "topica";
        String bchannel = "topicb";
        
        redisTemplate.convertAndSend(achannel, "hello world");
        
        redisTemplate.convertAndSend(bchannel, new AnnouncementMessage("1", "模拟发通告"));
    }
}
```

结果：

```bash
输出： 
A==>consumer message: hello world
A==>consumer message: AnnouncementMessage(id=1, content=模拟发通告)

B==>consumer message: AnnouncementMessage(id=1, content=模拟发通告)
```

因为A 消费者订阅两个频道，而B 消费者只订阅了一个频道，所以A 会多一条。

`注` :测试时需要把主启动类也给启动起来，方便查看输出。（主启动自己写就好了，没有什么其他的注解，普普通通的）



## 五、自言自语

不知道大家学习是什么样的，博主自己的感觉就是学了的东西，要通过自己去梳理一遍，或者说是去实践一遍，我觉得这样子，无论是对于理解还是记忆，都会更加深刻。

如若有不足之处，请不啬赐教！！😁

有疑惑之处，也可以留言或私信，定会第一时间回复。👩‍💻

这篇文章就到这里啦，下篇文章再见。👉一篇文章用Redis 实现消息队列（~~还在写~~）




