package com.crush.scheduled.taskscheduling;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务 静态定时任务
 *
 * 第一位，表示秒，取值0-59
 * 第二位，表示分，取值0-59
 * 第三位，表示小时，取值0-23
 * 第四位，日期天/日，取值1-31
 * 第五位，日期月份，取值1-12
 * 第六位，星期，取值1-7，1表示星期天，2表示星期一
 * 第七位，年份，可以留空，取值1970-2099
 * @author crush
 * @since 1.0.0
 * @Date: 2021-07-27 21:13
 */
@Component
public class SchedulingTaskBasic {

    /**
     * 每五秒执行一次
     */
    @Scheduled(cron = "*/5 * * * * ?")
    private void printNowDate() {
        long nowDateTime = System.currentTimeMillis();
        System.out.println("固定定时任务执行:--->"+nowDateTime+"，此任务为每五秒执行一次");
    }
}
