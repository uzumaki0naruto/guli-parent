package com.atguigu.edu.service;

import com.atguigu.edu.entity.EduSubject;

import com.atguigu.edu.entity.subject.oneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-29
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService subjectService);

//    获得所有课程
    List<oneSubject> getAllOneTwoSubject();
}
