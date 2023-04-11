package com.crush.queue;

import com.crush.queue.entity.AnnouncementMessage;
import com.crush.queue.service.MessageConsumerService;
import com.crush.queue.service.MessageProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * @Author: crush
 * @Date: 2021-08-06 17:11
 * version 1.0
 */
@SpringBootTest
public class MessageQueueTest {
    @Autowired
    private MessageProducerService producer;

    @Autowired
    private MessageConsumerService consumer;



    /**
     * 这个测时 的先启动主启动累，
     * 然后消费者可以一直在监听。
     */
    @Test
    public void testQueue2() {
        producer.sendMeassage(new AnnouncementMessage("1", "aaaa"));
        producer.sendMeassage(new AnnouncementMessage("2", "bbbb"));
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
