package com.crush.scheduled.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crush.scheduled.entity.Cron;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CronMapper extends BaseMapper<Cron> {
    @Select("select cron_expression from tb_cron where id=1")
    String getCron1();

}
