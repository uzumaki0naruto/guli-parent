package com.atguigu.edu.RpcClient;

import com.atguigu.commontuils.R;
import com.atguigu.orderVo.ucenterMemberVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-ucenter",fallback = ucenterClient.class)
public interface ucenterClient {
    @GetMapping("/getMemberInfo/{id}")
    public ucenterMemberVo getMemberInfoById(@PathVariable String id);



}
