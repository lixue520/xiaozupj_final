package com.crush.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crush.log.entity.LogOperation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author crush
 */
@Repository
@Mapper
public interface LogOperationMapper extends BaseMapper<LogOperation> {

}
