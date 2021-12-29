package com.atguigu.serviceucenter;

import com.atguigu.serviceucenter.entity.UcenterMember;
import com.atguigu.serviceucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceUcenterApplicationTests {
    @Autowired
    UcenterMemberService ucenterMemberService;



    @Test
    void contextLoads() {

    }

}
