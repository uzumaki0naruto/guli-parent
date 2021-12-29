package com.atguigu.service_statistics;

import com.atguigu.service_statistics.Scheduled.DateUtils;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ServiceStatisticsApplicationTests {

    @Test
    void contextLoads() throws ParseException {

        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat = DateFormat.getInstance();
        Calendar calendar = Calendar.getInstance();
        String date1="2019-01-07";   //bing
        String date2="2019-01-11";  //end
        Date begin1 = simpleDateFormat.parse(date1);
        String begin = simpleDateFormat.format(begin1);


        System.out.println(begin1);

//        Date end1= simpleDateFormat.parse(date2);
//        String end = simpleDateFormat.format(end1);
        String result="";
        List<String> dateList = new ArrayList<String>();
        dateList.add(date1);
        int i=1;

        while(!result.equals(date2)){
            System.out.println("...........");
            result = DateUtils.addDays(begin1, i);
            System.out.println(result+".....");
        dateList.add(result);
        i++;
        }


        System.out.println(result.equals(date2)+"////");
        System.out.println(dateList);

    }

}
