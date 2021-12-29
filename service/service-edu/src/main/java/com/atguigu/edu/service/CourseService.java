package com.atguigu.edu.service;

import com.atguigu.edu.entity.Course;
import com.atguigu.edu.entity.CourseInfoVo;
import com.atguigu.edu.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-10-22
 */


public interface CourseService extends IService<Course> {

    String saveSubjectInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseById(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourseById(String id);
}
