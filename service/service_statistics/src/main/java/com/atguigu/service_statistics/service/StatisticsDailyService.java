package com.atguigu.service_statistics.service;

import com.atguigu.service_statistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-12-13
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void registerCount(String day);

    Map<String,Object> registerCountVo(String type, String begin, String end) throws ParseException;
}
