package com.atguigu.service_statistics.Scheduled;

import com.atguigu.commontuils.R;
import com.atguigu.service_statistics.service.StatisticsDailyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Component
public class scheduleTask {

//    @RequestMapping("/schedule")
@Autowired
StatisticsDailyService statisticsDailyService;
//
//    @Scheduled(cron = "0/5 * * * * ?")
//        public void Schedule(){
//            System.out.println("task");
//
//        }

    @ApiOperation("获取注册人数")
    @Scheduled(cron = "0 0 1 * * ?")
    public void countRegistSchedule(){
        statisticsDailyService.registerCount(DateUtils.addDays(new Date(),-1));
    }

}
