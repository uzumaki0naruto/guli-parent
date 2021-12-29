package com.atguigu.edu.vod;

import com.atguigu.commontuils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "service-vod",fallback = vodService.class)
public interface vodClient {

    //定义调用的方法路径
    //根据视频id删除阿里云视频
    //@PathVariable注解一定要指定参数名称，否则出错
    @DeleteMapping("eduvod/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable("id") String id);


    //定义调用删除多个视频的方法
    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("/eduvod/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);




}
