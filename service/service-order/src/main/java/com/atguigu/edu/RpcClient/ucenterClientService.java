package com.atguigu.edu.RpcClient;

import com.atguigu.commontuils.R;
import com.atguigu.orderVo.ucenterMemberVo;
import org.springframework.stereotype.Component;

@Component
public class ucenterClientService implements ucenterClient{

    @Override
    public ucenterMemberVo getMemberInfoById(String id) {
        System.out.println("服务熔断,获取会员信息");
        return null;
    }
}
