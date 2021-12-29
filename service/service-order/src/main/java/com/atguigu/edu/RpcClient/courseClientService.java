package com.atguigu.edu.RpcClient;

import com.atguigu.commontuils.R;
import com.atguigu.orderVo.courseVo;
import org.springframework.stereotype.Component;

@Component
public class courseClientService implements courseClient{


    @Override
    public courseVo getCourseById(String courseId) {
        System.out.println("服务熔断,获取课程信息");
        return null;
    }
}
