package com.atguigu.edu.RpcClient;

import com.atguigu.commontuils.R;
import com.atguigu.orderVo.courseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name="service-edu" ,fallback = courseClientService.class)
public interface courseClient {

    @PostMapping("getCourseClient/{courseId}")
    public courseVo getCourseById(@PathVariable String courseId);
}
