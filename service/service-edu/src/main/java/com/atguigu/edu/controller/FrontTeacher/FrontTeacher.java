package com.atguigu.edu.controller.FrontTeacher;

import com.atguigu.commontuils.R;
import com.atguigu.edu.entity.Course;
import com.atguigu.edu.entity.Teacher;
import com.atguigu.edu.service.CourseService;
import com.atguigu.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/eduservice/FrontTeacher")
public class FrontTeacher {

    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;

    @GetMapping("/PageTeacher/{current}/{size}")
    public R getTeacher(@PathVariable(value = "current") Integer current, @PathVariable(value = "size")Integer size){
        Page<Teacher> page = new Page<Teacher>(current,size);
        Page<Teacher> teacherPage = teacherService.page(page, null);
        List<Teacher> teacherList = teacherPage.getRecords();
        long total = teacherPage.getTotal();
        return  R.ok().data("teacherList",teacherList).data("total",total);
    }
    @GetMapping("/getCourse/{Tid}")
    public R getCourseByTId(@PathVariable(value = "Tid") String Tid){
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("teacher_id",Tid);

        List<Course> CourseList = courseService.list(courseQueryWrapper);


        return R.ok().data("courseList",CourseList);
    }

}
