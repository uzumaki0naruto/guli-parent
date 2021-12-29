package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.Video;
import com.atguigu.edu.mapper.VideoMapper;
import com.atguigu.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-10-22
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
