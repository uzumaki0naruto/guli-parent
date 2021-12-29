package com.atguigu.service_statistics.controller;


import com.atguigu.commontuils.R;
import com.atguigu.service_statistics.service.StatisticsDailyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-12-13
 */
@RestController

@RequestMapping("/edusta/sta")
public class StatisticsDailyController {

    @Autowired
    StatisticsDailyService statisticsDailyService;

    @ApiOperation("获取某注册人数")
    @GetMapping("/countRegister/{day}")
    public R CountRegister(@PathVariable String day){
        statisticsDailyService.registerCount(day);
        return R.ok();
    }


    @ApiOperation("按条件获取一段时间内每一天的注册人数 数组")
    @GetMapping("/registerCount/{type}/{begin}/{end}")
    public R registerCount(@PathVariable("type") String type,
                           @PathVariable("begin") String begin,
                           @PathVariable("end")String end) throws ParseException {
        Map<String,Object> map=statisticsDailyService.registerCountVo(type,begin,end);
        return R.ok().data(map);
    }

}
