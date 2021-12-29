package com.atguigu.edu.service.impl;
import com.atguigu.edu.entity.Chapter;
import com.atguigu.edu.entity.Video;
import com.atguigu.edu.entity.chapter.chapterVo;
import com.atguigu.edu.entity.chapter.videoVo;
import com.atguigu.edu.mapper.ChapterMapper;
import com.atguigu.edu.service.ChapterService;
import com.atguigu.edu.service.VideoService;
import com.atguigu.exceptionhandler.guliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-10-22
 */

@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;



    @Override
    public List<chapterVo> getChapterVideo(String courseId) {

//        查询所有章节,并且封装
        QueryWrapper<Chapter> chapterVoQueryWrapper = new QueryWrapper<>();
        chapterVoQueryWrapper.eq("course_id",courseId);
        List<Chapter> chapterList = this.list(chapterVoQueryWrapper);
//查询所有小节并且封装
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        List<Video> videoList = videoService.list(videoQueryWrapper);
//        封装最终所有章节信息
        List<chapterVo> finalChapterVoList=new ArrayList<>();
//      封装所有小节信息

        for (Chapter chapter : chapterList) {
            chapterVo chapterVo = new chapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            finalChapterVoList.add(chapterVo);
            List<videoVo> finalVideoVoList=new ArrayList<>();
            for (Video video : videoList) {

                if(video.getChapterId().equals(chapter.getId())){

                    videoVo videoVo = new videoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    finalVideoVoList.add(videoVo);
                }
            }
            chapterVo.setVideoVoList(finalVideoVoList);
        }
        return finalChapterVoList;
    }

//删除章节,如果有小节就不删除,没有就删除
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterid章节id 查询小节表，如果查询数据，不进行删除
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        //判断
        if(count >0) {//查询出小节，不进行删除
            throw new guliException(20001,"不能删除");
        } else { //不能查询数据，进行删除
            //删除章节
            int result = baseMapper.deleteById(chapterId);
            //成功  1>0   0>0
            return result>0;
        }
    }

}

