package com.atguigu.serviceVod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.commontuils.R;
import com.atguigu.exceptionhandler.guliException;
import com.atguigu.serviceVod.Service.VodService;
import com.atguigu.serviceVod.Utils.InitVodCilent;
import com.atguigu.serviceVod.Utils.util;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/eduvod")
public class VodController {
    @Autowired
    VodService vodService;

    @PostMapping("uploadVideo")
    public R uploadVideo(MultipartFile file) throws Exception {
      String id= vodService.uploadVideo(file);
        return  R.ok().data("VideoId",id);
    }

    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return R.ok();
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(util.ACCESS_KEY_ID,util.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return R.ok();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }

//根据视频id获取凭证
    @GetMapping("/getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id){

        try {
            //        初始化
            DefaultAcsClient client = InitVodCilent.initVodClient(util.ACCESS_KEY_ID, util.ACCESS_KEY_SECRET);
//            获取request
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
//向request设置视频id
            request.setVideoId(id);
//            获取response,并放入请求
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);

            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth",playAuth);

        } catch (ClientException e) {
            throw new guliException(20001,"获取凭证失败");
        }
    }
}
