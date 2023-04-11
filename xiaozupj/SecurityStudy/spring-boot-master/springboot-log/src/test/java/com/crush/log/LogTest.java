package com.crush.log;

import com.crush.log.entity.LogOperation;
import com.crush.log.entity.LogUser;
import com.crush.log.mapper.LogOperationMapper;
import com.crush.log.mapper.LogUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: crush
 * @Date: 2021-08-14 9:58
 * version 1.0
 */

@SpringBootTest
public class LogTest {

    @Autowired
    LogOperationMapper logOperationMapper;

    @Autowired
    LogUserMapper userMapper;

    /**
     * 事务管理
     */
    @Transactional
    @Test
    public void testInsert2(){
        LogUser crush = new LogUser().setUsername("宁在春test").setPasswrod("987456");
        userMapper.insert(crush);
    }

    @Test
    public void test1() {
        LogOperation logOperation = new LogOperation()
                .setId("1")
                .setUserId("2")
                .setUsername("crush")
                .setLoginIp("test")
                .setOperation("test");
        logOperationMapper.insert(logOperation);

    }
}
