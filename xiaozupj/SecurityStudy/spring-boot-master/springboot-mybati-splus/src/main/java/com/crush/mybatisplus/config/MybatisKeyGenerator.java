package com.crush.mybatisplus.config;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 主键自动生成
 * @Author: crush
 * @Date: 2021-07-23 14:14
 */
@Slf4j
@Component
public class MybatisKeyGenerator implements IKeyGenerator {
 
	@Value("${server.worker-id}")
	private Integer workerId;
 
	@Value("${server.data-center-id}")
	private Integer dataCenterId;
 
	@Override
	public String executeSql(String incrementerName) {
		log.info("mybatis plus keyGenerator: " + incrementerName + "(" + workerId + "," + dataCenterId + ")");
		long uid = new SnowflakeIdWorker(workerId, dataCenterId).nextId();
		return "select " + uid + " from dual";
	}
}