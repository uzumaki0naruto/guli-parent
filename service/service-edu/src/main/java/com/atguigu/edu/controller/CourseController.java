package com.atguigu.edu.controller;


import com.atguigu.commontuils.R;
import com.atguigu.edu.entity.Course;
import com.atguigu.edu.entity.CourseInfoVo;
import com.atguigu.edu.entity.vo.CoursePublishVo;
import com.atguigu.edu.service.CourseService;
import com.atguigu.orderVo.courseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



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
@RequestMapping("/eduservice/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/addSubjectInfo")
    public R addSubjectInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = courseService.saveSubjectInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }

    @PostMapping("getCourseInfo/{courseId}")
    public R getCourseInfoById(@PathVariable String courseId){
       CourseInfoVo courseInfoVo= courseService.getCourseById(courseId);
       return R.ok().data("courseInfoVo",courseInfoVo);
    }


    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

//    根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }


    @PostMapping("/publishCourse/{id}")
    public R publishCourse(@PathVariable  String id){
//        CourseInfoVo courseById = courseService.getCourseById(id);
        Course course = courseService.getById(id);
        course.setStatus("Normal");
        courseService.updateById(course);
        return R.ok();
    }
//    获得所有课程
    @PostMapping("/getListCourse")
    public R getListCourse(){
        List<Course> list = courseService.list();
        return R.ok().data("list",list);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public R deleteCourse(@PathVariable String id){
        courseService.removeCourseById(id);
        return R.ok();
    }

    // courseclient,rpc使用
    @PostMapping("getCourseClient/{courseId}")
    public courseVo getCourseById(@PathVariable String courseId){
        CourseInfoVo courseInfoVo= courseService.getCourseById(courseId);
        courseVo courseVo = new courseVo();
        BeanUtils.copyProperties(courseInfoVo,courseVo);
        return courseVo;
    }
}

