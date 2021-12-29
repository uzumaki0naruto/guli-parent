package com.atguigu.edu.service;

import com.atguigu.edu.entity.Chapter;
import com.atguigu.edu.entity.chapter.chapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-10-22
 */
public interface ChapterService extends IService<Chapter> {

    List<chapterVo> getChapterVideo(String courseId);

    boolean deleteChapter(String chapterId);
}
