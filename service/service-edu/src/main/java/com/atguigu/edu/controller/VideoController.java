package com.atguigu.edu.controller;


import com.atguigu.commontuils.R;
import com.atguigu.edu.entity.Video;
import com.atguigu.edu.service.VideoService;
import com.atguigu.exceptionhandler.guliException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-10-22
 */
@RestController

@RequestMapping("/eduservice/video")
public class VideoController {

     @Autowired
     private VideoService videoService;
     @Autowired
     private com.atguigu.edu.vod.vodClient vodClient;

     @PostMapping("/addVideo")
     public R addVideo(@RequestBody Video video){
         videoService.save(video);
         return  R.ok();
     }

// 根据id删除视频
     @DeleteMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
         Video video = videoService.getById(id);
         String videoSourceId = video.getVideoSourceId();
         if(!StringUtils.isEmpty(videoSourceId)){
             R result = vodClient.removeAlyVideo(videoSourceId);
             String a="a";
             if(result.getCode()==20001){

                 throw new guliException(20001,"删除视频失败了，。。，。，、");
             }
         }
         videoService.removeById(id);
         return R.ok();
     }

//     根据id集合删除视频
    @DeleteMapping("/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList){

         if(videoIdList !=null){
             vodClient.deleteBatch(videoIdList);
         }

         return R.ok();
    }


     @PostMapping("/update")
    public R updateVideo(@RequestBody Video video) {
         videoService.updateById(video);
         return R.ok();
     }





}

