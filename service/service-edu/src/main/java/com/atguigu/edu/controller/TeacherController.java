package com.atguigu.edu.controller;


import com.atguigu.commontuils.R;
import com.atguigu.edu.entity.Teacher;
import com.atguigu.edu.entity.vo.TeacherQuery;
import com.atguigu.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-08-16
 */

@RestController
@RequestMapping("/eduservice/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @ApiOperation("按id删除")
    @DeleteMapping("/delete/{id}")
    public R removeTeacher(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation("获得所有讲师列表")
    @GetMapping("/selectAll")
    public R selectAllTeacher(){

//        return   teacherService.list(null);
        List<Teacher> list = teacherService.list(null);

        return R.ok().data("items",list);
    }


    @ApiOperation("分页")
    @GetMapping("/PageTeacher/{current}/{size}")
    public R findTeacherForPage(@PathVariable long current,
                                @PathVariable long size){
        Page<Teacher> teacherPage = new Page<>(current,size);
//方法一
//        Page<Teacher> page = teacherService.page(teacherPage, null);
//        long total = page.getTotal();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("total",total);
//        map.put("rows",page);
//        return R.ok().data("pageItems",map );
//        方法2
//        调用方法会把底层数据返回到teacherPage中
        teacherService.page(teacherPage, null);
        List<Teacher> records = teacherPage.getRecords();
        long total = teacherPage.getTotal();
        return R.ok().data("Total",total).data("Rows",records);
    }

    //require=false 条件类可以为空
    @ApiOperation("组合条件的分页查询")
    @PostMapping("/PageTeacherCondition/{current}/{size}")
    public R PageTeacherCondition(@PathVariable long current, @PathVariable long size,
             @RequestBody(required = false) TeacherQuery teacherQuery){
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        QueryWrapper<Teacher> Wrapper = new QueryWrapper<>();
        if(name!=null){
            Wrapper.like("name",name);
        }
        if (level!=null){
            Wrapper.eq("level",level);
        }
        if(begin!=null){
            Wrapper.ge("gmt_create",begin);
        }
        if (end!=null){
            Wrapper.le("gmt_create",end);
        }
//        Wrapper.orderByDesc("gmtCrreate");
//        1.创建page对象
        Page<Teacher> page = new Page<>(current, size);
        teacherService.page(page,Wrapper);
        List<Teacher> records = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }


    @ApiOperation("添加讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody Teacher teacher){
        boolean flag = teacherService.save(teacher);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }



    @ApiOperation("根据讲师id进行查询")
    @GetMapping("/getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }


    @ApiOperation("讲师的修改")
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody Teacher teacher){
        boolean b = teacherService.updateById(teacher);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }

    }




}

