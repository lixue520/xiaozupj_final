package com.crush.queue.service;

import com.crush.queue.entity.AnnouncementMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class MessageProducerService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private String queueKey="queue";
    
    public Long sendMeassage(AnnouncementMessage message) {
        System.out.println("发送了" + message);
        return redisTemplate.opsForList().leftPush(queueKey, message);
    }
    
}
