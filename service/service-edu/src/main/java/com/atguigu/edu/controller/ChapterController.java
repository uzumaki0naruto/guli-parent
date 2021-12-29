package com.atguigu.edu.controller;


import com.alibaba.excel.util.StringUtils;
import com.atguigu.commontuils.R;
import com.atguigu.edu.entity.Chapter;
import com.atguigu.edu.entity.Video;
import com.atguigu.edu.entity.chapter.chapterVo;
import com.atguigu.edu.service.ChapterService;
import com.atguigu.edu.service.VideoService;
import com.atguigu.edu.vod.vodClient;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-10-22
 */


@RestController
@RequestMapping("/eduservice/chapter")

public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private com.atguigu.edu.vod.vodClient vodClient;


    @PostMapping("/getChapterVideo/{courseId}")
    public R GetChapterVideo(@PathVariable String courseId){

       List<chapterVo> list=  chapterService.getChapterVideo(courseId);
        return R.ok().data("chapterList",list);
    }

    @PostMapping("/addChapter")
    public R addChapter(@RequestBody Chapter chapter){
        System.out.println("tianjia");
        chapterService.save(chapter);
        return  R.ok();
    }

    @GetMapping("/getChapter/{chapterId}")
    public R getChapterById(@PathVariable String chapterId){
        System.out.println("get");
        Chapter chapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",chapter);

    }

    @PostMapping("/updateChapter")
    public R  updateChapter(@RequestBody Chapter chapter){


     chapterService.updateById(chapter);
     return R.ok();
    }


    @PostMapping("/deleteChapter/{chapterId}")
    public R deleteChapterById(@PathVariable String chapterId){

        QueryWrapper<Video> wrapper = new QueryWrapper<Video>();
        wrapper.eq("chapter_id",chapterId);
        wrapper.select("video_source_id");

        List<Video> videoList = videoService.list(wrapper);
        List<String> videoIdList = new ArrayList<>();
        for (Video video : videoList) {
            String videoSourceId = video.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                videoIdList.add(videoSourceId);
            }

        }
        if(videoIdList.size()>0){

            vodClient.deleteBatch(videoIdList);
        }


        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id",chapterId);
        videoService.remove(videoQueryWrapper);


        boolean b = chapterService.deleteChapter(chapterId);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }

    }









}

