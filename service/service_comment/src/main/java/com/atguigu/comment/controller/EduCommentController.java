package com.atguigu.comment.controller;


import com.atguigu.comment.entity.EduComment;
import com.atguigu.comment.service.EduCommentService;
import com.atguigu.commontuils.R;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2022-03-20
 */
@RestController
@RequestMapping("/eduComment")
@Slf4j
public class EduCommentController {

    @Autowired
    EduCommentService eduCommentService;

    //显示课程下的全部评论
    @GetMapping("/selectRootPage/{cid}/{current}/{size}")
    public R rootCommentPage(@PathVariable String cid, @PathVariable int current,@PathVariable int size){
        log.info("showComment"+cid);
        QueryWrapper<EduComment> eduCommentQueryWrapper = new QueryWrapper<>();
        eduCommentQueryWrapper.eq("course_Id",cid);
        Page<EduComment> commentPage = new Page<EduComment>(current,size);
        Page<EduComment> page = eduCommentService.page(commentPage, eduCommentQueryWrapper);
        List<EduComment> records = page.getRecords();

        long total = page.getTotal();
        System.out.println("+++++"+records);
        return R.ok().data("rows",records).data("total",total);
    }

    @GetMapping("good")
    public R good(){
        System.out.println("good");
        return R.ok();
    }

    @PostMapping("/addComment")
    public R addComment(@RequestBody EduComment eduComment){
        log.info("发布评论");

        log.info("{}"+eduComment.getContent()+";{}"+eduComment.getMemberId());
        boolean save = eduCommentService.save(eduComment);

        return R.ok();
    }

//    查看所有根评论下的所有评论
    @GetMapping("/selectPage/{current}/{size}")
    public R CommentPage(String id, @PathVariable int current,@PathVariable int size){

        QueryWrapper<EduComment> eduCommentQueryWrapper = new QueryWrapper<>();
        eduCommentQueryWrapper.eq("fid",id);
        Page<EduComment> eduCommentPage = new Page<>(current,size);
        eduCommentService.page(eduCommentPage,eduCommentQueryWrapper);
        return R.ok().data("total",eduCommentPage.getTotal()).data("rows",eduCommentPage.getRecords());
    }


//
//         @PostMapping("saveComment")
//    //发布评论
//        public R commentPublish(EduComment eduComment){
//        log.info("发布评论");
//        log.info("{}"+eduComment.getContent()+";{}"+eduComment.getMemberId());
//            boolean b = eduCommentService.save(eduComment);
//            if(b){
//                return R.ok();
//            }else {
//                return R.error();
//            }
//        }

    //删除评论
    @PostMapping("removeComment")
    public R commentDelete(String id){

        boolean b = eduCommentService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }


    @GetMapping("/agreeComment")
//  点赞
    public R agreeComment(String id){
        EduComment comment = eduCommentService.getById(id);
        comment.setAgreeNumber(comment.getAgreeNumber()+1);
        return R.ok();
    }


//    取消点赞,或不赞同
    public R cancelComment(String id){
        EduComment comment = eduCommentService.getById(id);
        comment.setAgreeNumber(comment.getAgreeNumber()+1);
        return R.ok();
    }




}

