package com.atguigu.edu.mapper;

import com.atguigu.edu.entity.Course;
import com.atguigu.edu.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-10-22
 */
public interface CourseMapper extends BaseMapper<Course> {

   public  CoursePublishVo getPublishCourseInfo(String courseId);

}
