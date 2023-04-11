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
