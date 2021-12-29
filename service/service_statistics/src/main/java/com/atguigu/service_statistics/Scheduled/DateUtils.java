package com.atguigu.service_statistics.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String addDays(Date date,Integer day){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat Year = new SimpleDateFormat("yyyy");
        SimpleDateFormat Month = new SimpleDateFormat("MM");
        SimpleDateFormat Day = new SimpleDateFormat("dd");
        String month = Month.format(date);
//        System.out.println(month+"{{"+Day.format(date));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(Year.format(date)),Integer.parseInt(Month.format(date))-1,Integer.parseInt(Day.format(date)));
        
        calendar.add(5,day);
        String format= simpleDateFormat.format(calendar.getTime());
        System.out.println(format+"format");
        return format;
    }



}
