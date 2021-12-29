package com.atguigu.edu.controller.FrontTeacher;

import com.atguigu.commontuils.R;
import com.atguigu.edu.entity.Course;
import com.atguigu.edu.entity.EduSubject;
import com.atguigu.edu.entity.vo.front.CourseFrontVo;
import com.atguigu.edu.service.CourseService;
import com.atguigu.edu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/*
这个类是点击课程后
 */
@RestController

@RequestMapping("/eduservice/FrontCourse")
public class FrontCourse {

    @Autowired
    CourseService courseService;

    @Autowired
    EduSubjectService eduSubjectService;
    //获得所有的课程并且分类
    @PostMapping("/getCoursePage/{current}/{size}")
    public R getCoursePage(@PathVariable(value = "current") Integer current
            , @PathVariable(value = "size") Integer size,
               @RequestBody(required = false) CourseFrontVo courseFrontVo){
        System.out.println(courseFrontVo);

        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        Page<Course> coursePage = new Page<>(current,size);
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())) { //二级分类
            wrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getBuy_count())) { //关注度
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmt_create())) { //最新
            wrapper.orderByDesc("gmt_create");
        }

        Page<Course> page = courseService.page(coursePage, wrapper);
        List<Course> courseList = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("courseList",courseList).data("total",total);
    }



//    根据一级学科id获得所属的二级学科
    @GetMapping("/getTwoSubject/{pid}")
    public R getTwoSubject(@PathVariable(value = "pid") String pid){



        QueryWrapper<EduSubject> eduSubjectQueryWrapper = new QueryWrapper<>();
        eduSubjectQueryWrapper.eq("parent_id",pid);
        List<EduSubject> twoSubjectList = eduSubjectService.list(eduSubjectQueryWrapper);
        return R.ok().data("twoSubjectList",twoSubjectList);
    }



//根据二级学科分类获得课程并且分页
//    @GetMapping("/getTwoSubject/{cid}")
//    public R getSubJectCoursePage(@PathVariable(value = "cid") String cid){
//
//
//        return R.ok().data("courseList",courseList);
//    }





}
