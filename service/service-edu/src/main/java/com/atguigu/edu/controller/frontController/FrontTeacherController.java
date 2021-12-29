package com.atguigu.edu.controller.frontController;

import com.atguigu.commontuils.R;
import com.atguigu.edu.entity.Course;
import com.atguigu.edu.entity.Teacher;
import com.atguigu.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController

@RequestMapping("/eduservice/FrontTeacher")
public class FrontTeacherController {

    @Autowired
    private TeacherService teacherService;
    @RequestMapping("/getTeacher")
    public R getTeacher(){

        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();

        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<Teacher> teacherList = teacherService.list(teacherQueryWrapper);


        return R.ok().data("teacherList",teacherList);

    }

}
