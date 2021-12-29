package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.*;
import com.atguigu.edu.entity.vo.CoursePublishVo;
import com.atguigu.edu.mapper.CourseMapper;
import com.atguigu.edu.service.ChapterService;
import com.atguigu.edu.service.CourseDescriptionService;
import com.atguigu.edu.service.CourseService;
import com.atguigu.edu.service.VideoService;
import com.atguigu.exceptionhandler.guliException;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    VideoService videoService;
    @Autowired
    ChapterService chapterService;
    @Autowired
    CourseDescriptionService courseDescriptionService;

    @Override
    public void removeCourseById(String id) {
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",id);
        List<Video> list = videoService.list(videoQueryWrapper);

//
//        for (Video video : list) {
//            Video vdo = new Video();
//            video.setVideoSourceId(null);
//            video.setVideoOriginalName(null);
//            BeanUtils.copyProperties(video,vdo);
//            videoService.updateById(vdo);
//        }
        //        删除所有小节
        videoService.remove(videoQueryWrapper);

//        删除所有章节
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",id);
        chapterService.remove(chapterQueryWrapper);
//        删除所有描述
        QueryWrapper<CourseDescription> courseDescriptionQueryWrapper = new QueryWrapper<>();
        courseDescriptionQueryWrapper.eq("course_id",id);
        courseDescriptionService.removeById(id);
//        删除课程本身
        this.removeCourseById(id);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);

        return publishCourseInfo;
    }

  

//    添加课程基础信息
    @Override
    public String saveSubjectInfo(CourseInfoVo courseInfoVo){

        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        boolean flag = this.save(course);
        if(!flag){
            throw new guliException(20001,"添加失败");
        }
        String cid = course.getId();

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(cid);
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.save(courseDescription);
        return cid;


    }

    @Override
    public CourseInfoVo getCourseById(String courseId) {
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        Course course = this.getById(courseId);
        BeanUtils.copyProperties(course,courseInfoVo);
        CourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());
//        BeanUtils.copyProperties(courseDescription,courseInfoVo);
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1 修改课程表
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        System.out.println("this id"+course.getId());
        System.out.println("this.time"+course.getGmtCreate());
        System.out.println("this.time"+course.getGmtModified());
        boolean update = this.updateById(course);
        if(!update) {
            throw new guliException(20001,"修改课程信息失败");
        }

        //2 修改描述表
        CourseDescription description = new CourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);

    }
}
