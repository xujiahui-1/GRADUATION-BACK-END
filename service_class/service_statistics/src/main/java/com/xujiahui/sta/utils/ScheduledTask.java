package com.xujiahui.sta.utils;

import com.xujiahui.sta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * 毎日の1時、更新
     */
    @Scheduled(cron="0 0 1 * * ?")
    public void  task2(){
        statisticsDailyService.videocount
                    (DateUtils.formatDate(DateUtils.addDays(new Date(),-1)));
    }


}
