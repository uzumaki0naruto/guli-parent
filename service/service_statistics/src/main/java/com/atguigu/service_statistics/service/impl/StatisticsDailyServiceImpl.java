package com.atguigu.service_statistics.service.impl;

import com.atguigu.commontuils.R;

import com.atguigu.service_statistics.Scheduled.DateUtils;
import com.atguigu.service_statistics.entity.StatisticsDaily;
import com.atguigu.service_statistics.mapper.StatisticsDailyMapper;
import com.atguigu.service_statistics.service.StatisticsDailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-12-13
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {



    @Autowired
    com.atguigu.service_statistics.client.ucenterClient ucenterClient;


//    获取注册人数并更新到数据库中
    @Override
    public void registerCount(String day) {
        R result = ucenterClient.countRegister(day);
        Integer count = (Integer) result.getData().get("count");
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);
        if(count!=null){
            StatisticsDaily statisticsDaily = new StatisticsDaily();
            statisticsDaily.setRegisterNum(count);
            statisticsDaily.setDateCalculated(day);
            statisticsDaily.setLoginNum(RandomUtils.nextInt(100,300));
            statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100,300));
            statisticsDaily.setCourseNum(RandomUtils.nextInt(100,300));

            int insert = baseMapper.insert(statisticsDaily);
            System.out.println("save:"+insert);
        }else{
            System.out.println("获取注册人数失败");
        }

    }

    @Override
    public Map<String,Object> registerCountVo(String type, String begin, String end) throws ParseException {
        //根据条件查询对应数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
//        这其中所有的天数
        wrapper.between("date_calculated",begin,end);
//        指定查询的列
        wrapper.select("date_calculated",type);

        List<StatisticsDaily> staList = baseMapper.selectList(wrapper);

        //因为返回有两部分数据：日期 和 日期对应数量
        //前端要求数组json结构，对应后端java代码是list集合
        //创建两个list集合，一个日期list，一个数量list
        List<String> date_calculatedList = new ArrayList<>();
        List<Integer> numDataList = new ArrayList<>();

        //遍历查询所有数据list集合，进行封装
        for (int i = 0; i < staList.size(); i++) {
            StatisticsDaily daily = staList.get(i);
            //封装日期list集合
            date_calculatedList.add(daily.getDateCalculated());
            //封装对应数量
            switch (type) {
                case "login_num":
                    numDataList.add(daily.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    numDataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        //把封装之后两个list集合放到map集合，进行返回
        Map<String, Object> map = new HashMap<>();
        map.put("date_calculatedList",date_calculatedList);
        map.put("numDataList",numDataList);
        return map;
    }
}
