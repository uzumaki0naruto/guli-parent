package com.atguigu.service_statistics.client;

import com.atguigu.commontuils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-ucenter" )
public interface ucenterClient {

    @GetMapping("/educenter/member/countRegistet/{day}")
    public R countRegister(@PathVariable(value = "day") String day);
}
