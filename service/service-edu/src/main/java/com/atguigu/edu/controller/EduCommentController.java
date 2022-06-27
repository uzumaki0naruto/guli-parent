package com.atguigu.edu.controller;


import com.atguigu.commontuils.R;
import com.atguigu.edu.entity.EduComment;
import com.atguigu.edu.service.EduCommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2022-01-12
 */
@Controller
@RequestMapping("/eduservice/eduComment")
public class EduCommentController {

    @Autowired
    private EduCommentService eduCommentService;

    @PostMapping("/addComment")
    public R saveComment(@RequestBody EduComment eduComment){


        EduComment Comment = new EduComment();
        BeanUtils.copyProperties(eduComment,Comment);

        eduCommentService.save(Comment);

        return R.ok().data("comment",Comment);
    }


    @PostMapping("/removeComment/{id}")
    public R deleteCimment(@PathVariable String id){


        if(eduCommentService.removeById(id)) {
            return R.ok();
        }else{
            return R.error();
        }


    }


}

