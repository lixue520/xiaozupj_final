package com.crush.ps;

import com.crush.ps.entity.AnnouncementMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

/**
 * @Author: crush
 * @Date: 2021-08-06 15:13
 * version 1.0
 */
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
