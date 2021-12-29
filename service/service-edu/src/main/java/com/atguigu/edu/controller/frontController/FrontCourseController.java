package com.atguigu.edu.controller.frontController;


import com.atguigu.commontuils.R;
import com.atguigu.edu.entity.Course;
import com.atguigu.edu.service.CourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
这个类是首页
 */
@RestController

@RequestMapping("/eduservice/FrontCourse")
public class FrontCourseController {


    @Autowired
    private CourseService courseService;

    @RequestMapping("/getCourse")
    public R getCourse(){
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();

        courseQueryWrapper.orderByDesc("view_count");
        courseQueryWrapper.last("limit 8");
        List<Course> courseList = courseService.list(courseQueryWrapper);
        return R.ok().data("courseList",courseList);

    }






}
