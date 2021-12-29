package com.atguigu.service_statistics.client;

import com.atguigu.commontuils.R;

import org.springframework.stereotype.Component;

@Component
public class ucenterClientImpl implements ucenterClient{
    @Override
    public R countRegister(String day) {
        System.out.println("wrror");
        return R.error();
    }
}
